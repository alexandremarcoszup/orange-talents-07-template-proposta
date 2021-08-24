package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Bloqueio {

    @Column(name = "id_bloqueio")
    private String id;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private boolean ativo;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }
}
