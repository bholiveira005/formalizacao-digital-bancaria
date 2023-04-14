package br.com.bho.formalizacaodigital.service.implemention;

import br.com.bho.formalizacaodigital.domain.Produto;
import br.com.bho.formalizacaodigital.dto.ProdutoDTO;
import br.com.bho.formalizacaodigital.exception.ErroGeral;
import br.com.bho.formalizacaodigital.repository.ProdutoRepository;
import br.com.bho.formalizacaodigital.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> listar() {

        return produtoRepository.findAll().stream()
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNumeroProduto(),
                        produto.getTipo(),
                        produto.getNome(),
                        produto.getDataValidade()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO buscar(Long idProduto) {
        return produtoRepository.findById(idProduto)
                .map(produto -> new ProdutoDTO(
                        produto.getId(),
                        produto.getNumeroProduto(),
                        produto.getTipo(),
                        produto.getNome(),
                        produto.getDataValidade()
                )).orElseThrow(() -> new ErroGeral("Produto não encontrado. Favor Verificar"));
    }

    @Override
    public void salvar(ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findByNumeroProduto(produtoDTO.getNumeroProduto());
        if (ObjectUtils.isEmpty(produto)) {
            Produto produtoCadastro = new Produto();
            produtoCadastro.setNumeroProduto(produtoDTO.getNumeroProduto());
            produtoCadastro.setTipo(produtoDTO.getTipo());
            produtoCadastro.setNome(produtoDTO.getNome());
            produtoCadastro.setDataValidade(produtoDTO.getDataValidade());
            produtoRepository.save(produtoCadastro);
        } else {
            throw new ErroGeral("Produto: " + produto.getNome() + " está cadastrado com o número: " + produto.getNumeroProduto());
        }
    }

    @Override
    public void editar(ProdutoDTO produtoDTO) {
        Produto produtoEdicao = produtoRepository.findById(produtoDTO.getId())
                .orElseThrow(() -> new ErroGeral("Produto não encontrado. Favor Verificar"));
        produtoEdicao.setNumeroProduto(produtoDTO.getNumeroProduto());
        produtoEdicao.setTipo(produtoDTO.getTipo());
        produtoEdicao.setNome(produtoDTO.getNome());
        produtoEdicao.setDataValidade(produtoDTO.getDataValidade());
        produtoRepository.save(produtoEdicao);
    }

    @Override
    public void deletar(Long idProduto) {
        boolean existeProduto = produtoRepository.existsById(idProduto);
        if (existeProduto) {
            produtoRepository.deleteById(idProduto);
        } else {
            throw new ErroGeral("Produto não encontrado. Favor Verificar");
        }
    }
}
