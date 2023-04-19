package br.com.bho.formalizacaodigital.repository;

import br.com.bho.formalizacaodigital.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUserNameIgnoreCase(String userName);
}