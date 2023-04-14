package br.com.bho.formalizacaodigital.service;

import br.com.bho.formalizacaodigital.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> listar();

    ClienteDTO buscar(Long idCliente);

    void salvar(ClienteDTO cliente);

    void editar(ClienteDTO cliente);

    void deletar(Long idCliente);
}
