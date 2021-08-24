package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.modelo.Vencimento;

import java.time.LocalDateTime;

public class VencimentoResponseClient {

    private String id;

    private Integer dia;

    private LocalDateTime dataDeCriacao;

    public VencimentoResponseClient(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Vencimento responseClientToDomain() {
        return new Vencimento(this.id, this.dia, this.dataDeCriacao);
    }
}
