package br.com.bho.formalizacaodigital.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratacaoProdutoDTO implements Serializable {

    @NotNull
    private Long idContratacao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ClienteDTO clienteDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private SimulacaoProduto simulacaoProduto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String numeroProdutoContratado;

    @NotNull
    private String mensagemContratatacao;
}
