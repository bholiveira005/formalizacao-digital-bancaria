package br.com.bho.formalizacaodigital.controller;

import br.com.bho.formalizacaodigital.domain.Cliente;
import br.com.bho.formalizacaodigital.domain.ContratacaoProduto;
import br.com.bho.formalizacaodigital.domain.Produto;
import br.com.bho.formalizacaodigital.service.ContratacaoProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contratacao")
@AllArgsConstructor
public class ContratacaoDigitalController {

    ContratacaoProdutoService contratacaoProdutoService;

    @PostMapping("simulacao")
    public ResponseEntity<ContratacaoProduto> simularContratacao(Cliente cliente, Produto produto){
        return ResponseEntity.ok().body(contratacaoProdutoService.simularContratacao(cliente, produto));
    }

    @PostMapping("formalizacaq")
    public ResponseEntity<ContratacaoProduto> formalizarContratacao(Cliente cliente, Produto produto){
        return ResponseEntity.ok().body(contratacaoProdutoService.formalizarContratacao(cliente, produto));
    }
}
