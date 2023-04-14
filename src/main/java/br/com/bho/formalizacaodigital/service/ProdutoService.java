package br.com.bho.formalizacaodigital.service;

import br.com.bho.formalizacaodigital.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoService {

    List<ProdutoDTO> listar();

    ProdutoDTO buscar(Long idProduto);

    void salvar(ProdutoDTO produto);

    void editar(ProdutoDTO produto);

    void deletar(Long idProduto);
}
