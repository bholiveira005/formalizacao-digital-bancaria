package br.com.bho.formalizacaodigital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratacaoProdutoDTO implements Serializable {

    @NotNull
    private Long idContratacao;

    @NotNull
    private Long idCliente;

    @NotNull
    private Long idProduto;

    @NotNull
    private Long numeroContratacao;

    @NotNull
    private LocalDate dataContratacao;

    private LocalDate dataAtualizacao;

    @NotNull
    private Boolean contratacaoAtiva;
}
