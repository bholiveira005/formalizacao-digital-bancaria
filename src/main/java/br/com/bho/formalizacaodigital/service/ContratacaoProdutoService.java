package br.com.bho.formalizacaodigital.service;

import br.com.bho.formalizacaodigital.domain.Cliente;
import br.com.bho.formalizacaodigital.domain.ContratacaoProduto;
import br.com.bho.formalizacaodigital.domain.Produto;

public interface ContratacaoProdutoService {

    ContratacaoProduto simularContratacao(Cliente cliente, Produto produto);

    ContratacaoProduto formalizarContratacao(Cliente cliente, Produto produto);
}
