package br.com.bho.formalizacaodigital.dto;

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
public class ClienteDTO implements Serializable {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpfCnpj;

    @NotNull
    private Character sexo;

    @NotNull
    private Integer idade;

    @NotBlank
    private String profissao;

    @NotNull
    private BigDecimal salario;
}