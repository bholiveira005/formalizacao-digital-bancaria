package br.com.bho.formalizacaodigital.service.implemention;

import br.com.bho.formalizacaodigital.domain.Cliente;
import br.com.bho.formalizacaodigital.dto.ClienteDTO;
import br.com.bho.formalizacaodigital.exception.ErroGeral403;
import br.com.bho.formalizacaodigital.exception.ErroGeral404;
import br.com.bho.formalizacaodigital.repository.ClienteRepository;
import br.com.bho.formalizacaodigital.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteDTO> listar() {
        return clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO buscar(Long idCliente) {
        return clienteRepository.findById(idCliente).map(ClienteDTO::new)
                .orElseThrow(() -> new ErroGeral404("Cliente não encontrado. Favor Verificar"));
    }

    @Override
    public void salvar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findByCpfCnpj(clienteDTO.getCpfCnpj());
        if (ObjectUtils.isEmpty(cliente)) {
            Cliente clienteCadastro = new Cliente();
            clienteCadastro.setNome(clienteDTO.getNome());
            clienteCadastro.setCpfCnpj(clienteDTO.getCpfCnpj());
            clienteCadastro.setSexo(clienteDTO.getSexo());
            clienteCadastro.setIdade(clienteDTO.getIdade());
            clienteCadastro.setProfissao(clienteDTO.getProfissao());
            clienteCadastro.setSalario(clienteDTO.getSalario());
            clienteRepository.save(clienteCadastro);
        } else {
            throw new ErroGeral403("O cliente " + clienteDTO.getNome() + " já está cadastrado com o número de CPF/CNPJ: " + clienteDTO.getCpfCnpj());
        }
    }

    @Override
    public void editar(ClienteDTO clienteDTO) {
        Cliente clienteEdicao = clienteRepository.findById(clienteDTO.getIdCliente())
                .orElseThrow(() -> new ErroGeral404("Cliente não encontrado. Favor Verificar"));
        clienteEdicao.setNome(clienteDTO.getNome());
        clienteEdicao.setCpfCnpj(clienteEdicao.getCpfCnpj());
        clienteEdicao.setSexo(clienteDTO.getSexo());
        clienteEdicao.setIdade(clienteDTO.getIdade());
        clienteEdicao.setProfissao(clienteDTO.getProfissao());
        clienteEdicao.setSalario(clienteDTO.getSalario());
        clienteRepository.save(clienteEdicao);
    }

    @Override
    public void deletar(Long idCliente) {
        boolean existeCliente = clienteRepository.existsById(idCliente);
        if (existeCliente) {
            clienteRepository.deleteById(idCliente);
        } else {
            throw new ErroGeral404("Cliente não encontrado. Favor Verificar");
        }
    }
}
