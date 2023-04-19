package br.com.bho.formalizacaodigital.dto;

import br.com.bho.formalizacaodigital.domain.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

    private Long idProduto;

    @NotBlank
    private String tipo;

    @NotBlank
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal valorLimite;

    public ProdutoDTO(Produto produto) {
        this.idProduto = produto.getId();
        this.tipo = produto.getTipo();
        this.nome = produto.getNome();
        this.valorLimite = produto.getValorLimite();
    }
}
