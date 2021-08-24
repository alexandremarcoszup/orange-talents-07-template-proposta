package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
public class Renegociacao {

    @Column(name = "id_renegociacao")
    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    @Column(name = "data_de_cricao_renegociacao")
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Renegociacao() {
    }

    public Renegociacao(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }
}
