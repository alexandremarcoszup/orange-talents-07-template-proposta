package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Parcela {

    @Column(name = "id_parcela")

    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    @Deprecated
    public Parcela() {
    }

    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }
}
