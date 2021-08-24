package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.modelo.Bloqueio;

import java.time.LocalDateTime;

public class BloqueioResponseClient {

    private String id;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private boolean ativo;

    public BloqueioResponseClient(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio responseClientToDomain() {
        return new Bloqueio(this.id, this.bloqueadoEm, this.sistemaResponsavel, this.ativo);
    }
}
