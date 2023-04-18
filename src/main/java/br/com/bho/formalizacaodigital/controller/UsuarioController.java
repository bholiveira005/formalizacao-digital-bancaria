package br.com.bho.formalizacaodigital.controller;

import br.com.bho.formalizacaodigital.dto.UsuarioDTO;
import br.com.bho.formalizacaodigital.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping("cadastro")
    public void cadastrar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.cadastrar(usuarioDTO);
    }

    @PutMapping("edicao")
    public void editar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.editar(usuarioDTO);
    }

    @GetMapping("detalhe")
    public UsuarioDTO buscarDetalheUsuario(@RequestParam String userName) {
        return usuarioService.buscarDetalheUsuario(userName);
    }

    @GetMapping("role")
    public String buscarRole(@RequestParam String userName) {
        return usuarioService.buscarRole(userName);
    }
}
