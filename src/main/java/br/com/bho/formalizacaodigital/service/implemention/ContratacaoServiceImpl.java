package br.com.bho.formalizacaodigital.service.implemention;

import br.com.bho.formalizacaodigital.domain.Cliente;
import br.com.bho.formalizacaodigital.domain.ContratacaoProduto;
import br.com.bho.formalizacaodigital.domain.Produto;
import br.com.bho.formalizacaodigital.service.ContratacaoProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContratacaoServiceImpl implements ContratacaoProdutoService {
    @Override
    public ContratacaoProduto simularContratacao(Cliente cliente, Produto produto) {
        return null;
    }

    @Override
    public ContratacaoProduto formalizarContratacao(Cliente cliente, Produto produto) {
        return null;
    }
}
