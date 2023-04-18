package br.com.bho.formalizacaodigital.repository;

import br.com.bho.formalizacaodigital.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByCpfCnpj(String cpfCnpj);

    Optional<Cliente> findByIdAndCpfCnpj(Long id, String cpfCnpj);
}