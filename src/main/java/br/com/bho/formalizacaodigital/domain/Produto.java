package br.com.bho.formalizacaodigital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "numero", nullable = false)
    private Long numeroProduto;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;
}
