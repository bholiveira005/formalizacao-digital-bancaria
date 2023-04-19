package br.com.bho.formalizacaodigital.controller;

import br.com.bho.formalizacaodigital.dto.UsuarioDTO;
import br.com.bho.formalizacaodigital.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.cadastrar(usuarioDTO);
    }

    @PutMapping("edicao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.editar(usuarioDTO);
    }

    @GetMapping("detalhe")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO buscarDetalheUsuario(@RequestParam String userName) {
        return usuarioService.buscarDetalheUsuario(userName);
    }

    @GetMapping("role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String buscarRole(@RequestParam String userName) {
        return usuarioService.buscarRole(userName);
    }
}
