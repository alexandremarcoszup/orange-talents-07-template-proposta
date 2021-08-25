package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.modelo.Renegociacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoResponseClient {

    private String id;

    private Integer quantidade;

    private BigDecimal valor;

    private LocalDateTime dataDeCriacao;

    public RenegociacaoResponseClient(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Renegociacao responseClientToDomain() {
        return new Renegociacao(this.id, this.quantidade, this.valor, this.dataDeCriacao);
    }


}
