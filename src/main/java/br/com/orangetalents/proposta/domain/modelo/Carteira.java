package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Carteira {

    @Column(name = "id_carteira")
    private String id;

    private String email;

    private LocalDateTime associadaEm;

    private String emissor;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }
}
