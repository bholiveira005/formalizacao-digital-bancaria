package br.com.bho.formalizacaodigital.service;

import br.com.bho.formalizacaodigital.dto.ClienteDTO;
import br.com.bho.formalizacaodigital.dto.ContratacaoProdutoDTO;
import br.com.bho.formalizacaodigital.dto.SimulacaoProduto;

import java.util.List;

public interface ContratacaoProdutoService {

    List<SimulacaoProduto> simularContratacao(ClienteDTO cliente);

    ContratacaoProdutoDTO formalizarContratacao(ClienteDTO cliente, Long idProduto);
}
