package br.com.bho.formalizacaodigital.service.implemention;

import br.com.bho.formalizacaodigital.domain.Produto;
import br.com.bho.formalizacaodigital.dto.ProdutoDTO;
import br.com.bho.formalizacaodigital.exception.ErroGeral403;
import br.com.bho.formalizacaodigital.exception.ErroGeral404;
import br.com.bho.formalizacaodigital.repository.ProdutoRepository;
import br.com.bho.formalizacaodigital.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll().stream().map(ProdutoDTO::new).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscar(Long idProduto) {
        return produtoRepository.findById(idProduto).map(ProdutoDTO::new)
                .orElseThrow(() -> new ErroGeral404("Produto não encontrado. Favor Verificar"));
    }

    @Override
    public void salvar(ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findByNomeIgnoreCase(produtoDTO.getNome());
        if (Objects.isNull(produto)) {
            Produto produtoCadastro = new Produto();
            produtoCadastro.setTipo(produtoDTO.getTipo());
            produtoCadastro.setNome(produtoDTO.getNome());
            produtoCadastro.setValorLimite(produtoDTO.getValorLimite());
            produtoRepository.save(produtoCadastro);
        } else {
            throw new ErroGeral403("Produto: " + produto.getNome() + " já está cadastrado");
        }
    }

    @Override
    public void editar(ProdutoDTO produtoDTO) {
        Produto produtoEdicao = produtoRepository.findById(produtoDTO.getIdProduto())
                .orElseThrow(() -> new ErroGeral404("Produto não encontrado. Favor Verificar"));
        produtoEdicao.setTipo(produtoDTO.getTipo());
        produtoEdicao.setNome(produtoDTO.getNome());
        produtoEdicao.setValorLimite(produtoDTO.getValorLimite());
        produtoRepository.save(produtoEdicao);
    }

    @Override
    public void deletar(Long idProduto) {
        boolean existeProduto = produtoRepository.existsById(idProduto);
        if (existeProduto) {
            produtoRepository.deleteById(idProduto);
        } else {
            throw new ErroGeral404("Produto não encontrado. Favor Verificar");
        }
    }
}
