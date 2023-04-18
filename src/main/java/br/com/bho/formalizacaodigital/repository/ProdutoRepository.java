package br.com.bho.formalizacaodigital.repository;

import br.com.bho.formalizacaodigital.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Produto findByNomeIgnoreCase(String nome);
}