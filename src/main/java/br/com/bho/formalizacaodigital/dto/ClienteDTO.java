package br.com.bho.formalizacaodigital.dto;

import br.com.bho.formalizacaodigital.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

    private Long idCliente;

    @NotNull
    private String nome;

    @NotNull
    private String cpfCnpj;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Character sexo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String profissao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal salario;

    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getId();
        this.nome = cliente.getNome();
        this.cpfCnpj = cliente.getCpfCnpj();
        this.sexo = cliente.getSexo();
        this.idade = cliente.getIdade();
        this.profissao = cliente.getProfissao();
        this.salario = cliente.getSalario();
    }
}