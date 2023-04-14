package br.com.bho.formalizacaodigital.controller;

import br.com.bho.formalizacaodigital.dto.ProdutoDTO;
import br.com.bho.formalizacaodigital.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("lista")
    public ResponseEntity<List<ProdutoDTO>> listar(){
        return ResponseEntity.ok().body(produtoService.listar());
    }

    @GetMapping("pesquisa")
    public ResponseEntity<ProdutoDTO> buscar(@RequestParam Long idProduto){
        return ResponseEntity.ok().body(produtoService.buscar(idProduto));
    }

    @PostMapping("")
    public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody ProdutoDTO ProdutoDTO){
        produtoService.salvar(ProdutoDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("")
    public ResponseEntity<ProdutoDTO> editar(@RequestBody ProdutoDTO ProdutoDTO){
        produtoService.editar(ProdutoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> deletar(@RequestParam Long idProduto){
        produtoService.deletar(idProduto);
        return ResponseEntity.noContent().build();
    }
}
