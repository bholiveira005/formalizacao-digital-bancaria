package br.com.bho.formalizacaodigital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimulacaoProduto {

    private Long idProduto;

    private String nomeProduto;

    private BigDecimal limite;

    private Boolean aprovado;
}
