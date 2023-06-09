package br.com.bho.formalizacaodigital.controller;

import br.com.bho.formalizacaodigital.dto.ClienteDTO;
import br.com.bho.formalizacaodigital.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("lista")
    public ResponseEntity<List<ClienteDTO>> listar(){
        return ResponseEntity.ok().body(clienteService.listar());
    }

    @GetMapping("pesquisa")
    public ResponseEntity<ClienteDTO> buscar(@RequestParam Long idCliente){
        return ResponseEntity.ok().body(clienteService.buscar(idCliente));
    }

    @PostMapping("")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ClienteDTO clienteDTO){
        clienteService.salvar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("")
    public ResponseEntity<?> editar(@RequestBody @Valid ClienteDTO clienteDTO){
        clienteService.editar(clienteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> deletar(@RequestParam Long idCliente){
        clienteService.deletar(idCliente);
        return ResponseEntity.noContent().build();
    }
}
