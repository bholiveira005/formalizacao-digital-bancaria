package br.com.bho.formalizacaodigital.config;

import br.com.bho.formalizacaodigital.domain.Produto;
import br.com.bho.formalizacaodigital.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final ProdutoRepository produtoRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (CollectionUtils.isEmpty(produtoRepository.findAll())) {
            Produto cartaoBronze = new Produto();
            cartaoBronze.setNome("Cartão Bronze");
            cartaoBronze.setTipo("CARTAO_CREDITO");
            cartaoBronze.setValorLimite(BigDecimal.valueOf(5000.00));
            produtoRepository.save(cartaoBronze);

            Produto cartaoPrata = new Produto();
            cartaoPrata.setNome("Cartão Prata");
            cartaoPrata.setTipo("CARTAO_CREDITO");
            cartaoPrata.setValorLimite(BigDecimal.valueOf(10000.00));
            produtoRepository.save(cartaoPrata);

            Produto cartaoOuro = new Produto();
            cartaoOuro.setNome("Cartão Ouro");
            cartaoOuro.setTipo("CARTAO_CREDITO");
            cartaoOuro.setValorLimite(BigDecimal.valueOf(15000.00));
            produtoRepository.save(cartaoOuro);
        }
    }
}
