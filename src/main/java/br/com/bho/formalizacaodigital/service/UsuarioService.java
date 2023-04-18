package br.com.bho.formalizacaodigital.service;

import br.com.bho.formalizacaodigital.dto.UsuarioDTO;

public interface UsuarioService {
    void cadastrar(UsuarioDTO usuarioDTO);

    void editar(UsuarioDTO usuarioDTO);

    UsuarioDTO buscarDetalheUsuario(String userName);

    String buscarRole(String username);
}
