package br.com.bho.formalizacaodigital.controller;

import br.com.bho.formalizacaodigital.dto.UsuarioDTO;
import br.com.bho.formalizacaodigital.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        usuarioService.cadastrar(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("edicao")
    public ResponseEntity<?> editar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        usuarioService.editar(usuarioDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("detalhe")
    public ResponseEntity<UsuarioDTO> buscarDetalheUsuario(@RequestParam String userName) {
        return ResponseEntity.ok().body(usuarioService.buscarDetalheUsuario(userName));
    }

    @GetMapping("role")
    public ResponseEntity<String> buscarRole(@RequestParam String userName) {
        return ResponseEntity.ok().body(usuarioService.buscarRole(userName));
    }
}
