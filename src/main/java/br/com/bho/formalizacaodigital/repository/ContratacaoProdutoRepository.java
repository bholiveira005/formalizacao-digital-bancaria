package br.com.bho.formalizacaodigital.repository;

import br.com.bho.formalizacaodigital.domain.ContratacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratacaoProdutoRepository extends JpaRepository<ContratacaoProduto, Long> {
}