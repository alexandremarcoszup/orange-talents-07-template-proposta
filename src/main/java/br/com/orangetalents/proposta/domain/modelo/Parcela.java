package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Parcela {

    @Id
    @Column(insertable = true, updatable = true, nullable = false)
    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Parcela() {
    }

    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }
}
