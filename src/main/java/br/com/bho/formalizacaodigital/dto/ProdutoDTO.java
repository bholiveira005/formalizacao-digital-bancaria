package br.com.bho.formalizacaodigital.dto;

import br.com.bho.formalizacaodigital.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {

    @NotNull
    private Long idCliente;

    @NotBlank
    private String tipo;

    @NotBlank
    private String nome;

    private BigDecimal valorLimite;

    public ProdutoDTO(Produto produto) {
        this.idCliente = produto.getId();
        this.tipo = produto.getTipo();
        this.nome = produto.getNome();
        this.valorLimite = produto.getValorLimite();
    }
}
