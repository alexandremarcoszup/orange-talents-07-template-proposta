package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.modelo.Parcela;

import java.math.BigDecimal;

public class ParcelaResponseClient {

    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    public ParcelaResponseClient(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Parcela responseClientToDomain() {
        return new Parcela(this.id, this.quantidade, this.valor);
    }
}
