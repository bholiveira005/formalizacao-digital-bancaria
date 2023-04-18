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
@Table(name = "contratacao_produto")
public class ContratacaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idContratacao;

    @OneToOne
    @JoinColumn(name="cliente_id", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name="produto_id", nullable = false)
    private Produto produto;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "numero_contratacao", nullable = false)
    private String numeroContratacaoProduto;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "contratacao_ativa", nullable = false)
    private Boolean contratacaoAtiva;

    @Column(name = "data_contratacao", nullable = false)
    private LocalDate dataContratacao;
}
