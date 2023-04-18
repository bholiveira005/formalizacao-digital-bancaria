package br.com.bho.formalizacaodigital.service.implemention;

import br.com.bho.formalizacaodigital.domain.Cliente;
import br.com.bho.formalizacaodigital.domain.ContratacaoProduto;
import br.com.bho.formalizacaodigital.domain.Produto;
import br.com.bho.formalizacaodigital.dto.ClienteDTO;
import br.com.bho.formalizacaodigital.dto.ContratacaoProdutoDTO;
import br.com.bho.formalizacaodigital.dto.SimulacaoProduto;
import br.com.bho.formalizacaodigital.exception.ErroGeral403;
import br.com.bho.formalizacaodigital.exception.ErroGeral404;
import br.com.bho.formalizacaodigital.repository.ClienteRepository;
import br.com.bho.formalizacaodigital.repository.ContratacaoProdutoRepository;
import br.com.bho.formalizacaodigital.repository.ProdutoRepository;
import br.com.bho.formalizacaodigital.service.ContratacaoProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ContratacaoServiceImpl implements ContratacaoProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final ContratacaoProdutoRepository contratacaoProdutoRepository;

    @Override
    public List<SimulacaoProduto> simularContratacao(ClienteDTO clienteDTO) {
        List<SimulacaoProduto> simulacaoProdutos = new ArrayList<>();

        Cliente cliente = clienteRepository.findByIdAndCpfCnpj(clienteDTO.getIdCliente(), clienteDTO.getCpfCnpj())
                .orElseThrow(() -> new ErroGeral404("O Cliente com o CPF:" + clienteDTO.getCpfCnpj() + " não foi encontrado. Favor verificar."));

        List<Produto> produtos = Optional.of(produtoRepository.findAll())
                .orElseThrow(() -> new ErroGeral404("Nenhum Produto foi encontrado. Favor verificar."));

        produtos.forEach(cartao -> {
            BigDecimal rendaCliente = cliente.getSalario().setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal valorCartao = cartao.getValorLimite().setScale(2, RoundingMode.HALF_EVEN);
            boolean aprovado = rendaCliente.compareTo(valorCartao) >= 0;
            simulacaoProdutos.add(new SimulacaoProduto(cartao.getId(), cartao.getNome(), valorCartao, aprovado));
        });

        return simulacaoProdutos;
    }

    @Override
    public ContratacaoProdutoDTO formalizarContratacao(ClienteDTO clienteDTO, Long idProduto) {
        ContratacaoProdutoDTO contratacaoProdutoDTO = new ContratacaoProdutoDTO();

        Cliente cliente = clienteRepository.findByIdAndCpfCnpj(clienteDTO.getIdCliente(), clienteDTO.getCpfCnpj())
                .orElseThrow(() -> new ErroGeral404("O Cliente com o CPF:" + clienteDTO.getCpfCnpj() + " não foi encontrado. Favor verificar."));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ErroGeral404("Nenhum Produto foi encontrado. Favor verificar."));

        boolean existeProdutoContradoCliente = contratacaoProdutoRepository.findAll().stream()
                .anyMatch(cp -> Objects.equals(cp.getCliente().getId(), clienteDTO.getIdCliente()) && Objects.equals(cp.getProduto().getId(), idProduto));
        if (existeProdutoContradoCliente) {
            throw new ErroGeral403("Já existe o produto " + produto.getNome() + " cadastrado para o cliente " + cliente.getNome());
        }

        SimulacaoProduto simulacaoProduto = simularContratacao(clienteDTO).stream()
                .filter(contratacao -> contratacao.getIdProduto().equals(idProduto) && contratacao.getAprovado().equals(true)).findFirst()
                .orElseThrow(() -> new ErroGeral403("Renda não atinge o valor mínimo para aquisição do Cartão de Crédito " + produto.getNome()));

        ContratacaoProduto contratacaoProduto = new ContratacaoProduto();
        contratacaoProduto.setCliente(cliente);
        contratacaoProduto.setProduto(produto);
        contratacaoProduto.setNumeroContratacaoProduto(String.format("%016d", cliente.getId()));
        contratacaoProduto.setDataValidade(LocalDate.now().plusYears(5));// 05 anos de validade
        contratacaoProduto.setDataContratacao(LocalDate.now());
        contratacaoProduto.setContratacaoAtiva(true);
        contratacaoProdutoRepository.save(contratacaoProduto);

        contratacaoProdutoDTO.setIdContratacao(contratacaoProduto.getIdContratacao());
        contratacaoProdutoDTO.setClienteDTO(clienteDTO);
        contratacaoProdutoDTO.setSimulacaoProduto(simulacaoProduto);
        contratacaoProduto.setNumeroContratacaoProduto(contratacaoProduto.getNumeroContratacaoProduto());
        contratacaoProdutoDTO.setMensagemContratatacao("Parabéns pela aquisição do Cartão de Crédito.");
        return contratacaoProdutoDTO;
    }
}
