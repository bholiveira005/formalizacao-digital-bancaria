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

    @Column(name = "idCliente", nullable = false)
    private Long idCliente;

    @Column(name = "idProduto", nullable = false)
    private Long idProduto;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "numero_contratacao", nullable = false)
    private Long numeroContratacao;

    @Column(name = "data_contratacao", nullable = false)
    private LocalDate dataContratacao;

    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;

    @Column(name = "contratacao_ativa", nullable = false)
    private Boolean contratacaoAtiva;
}
