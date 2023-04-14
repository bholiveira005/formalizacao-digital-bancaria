package br.com.bho.formalizacaodigital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf_cnpj", nullable = false)
    private String cpfCnpj;

    @Column(name = "sexo", nullable = false)
    private Character sexo;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "profissao", nullable = false)
    private String profissao;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;
}