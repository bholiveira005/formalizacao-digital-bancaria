package br.com.bho.formalizacaodigital.controller;

import br.com.bho.formalizacaodigital.dto.ClienteDTO;
import br.com.bho.formalizacaodigital.dto.ContratacaoProdutoDTO;
import br.com.bho.formalizacaodigital.dto.SimulacaoProduto;
import br.com.bho.formalizacaodigital.service.ContratacaoProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("contratacao")
@AllArgsConstructor
public class ContratacaoDigitalController {

    ContratacaoProdutoService contratacaoProdutoService;

    @PostMapping("simulacao")
    public ResponseEntity<List<SimulacaoProduto>> simularContratacao(@RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.ok().body(contratacaoProdutoService.simularContratacao(clienteDTO));
    }

    @PostMapping("formalizacao")
    public ResponseEntity<ContratacaoProdutoDTO> formalizarContratacao(@RequestBody @Valid ClienteDTO clienteDTO, @RequestParam Long idProduto) {
        return ResponseEntity.ok().body(contratacaoProdutoService.formalizarContratacao(clienteDTO, idProduto));
    }
}
