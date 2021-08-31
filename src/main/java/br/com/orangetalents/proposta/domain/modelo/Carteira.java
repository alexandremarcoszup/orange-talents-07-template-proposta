package br.com.orangetalents.proposta.domain.modelo;

import br.com.orangetalents.proposta.controller.response.CarteiraResponse;
import br.com.orangetalents.proposta.domain.enums.TipoCarteira;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Carteira {

    @Id
    @Column(insertable = true, updatable = true, nullable = false)
    private String id;

    private String email;

    private LocalDateTime associadaEm;

    @Enumerated(EnumType.STRING)
    private TipoCarteira emissor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String id, String email, TipoCarteira emissor) {
        this.id = id;
        this.email = email;
        this.emissor = emissor;
    }

    public Carteira(String id, String email, TipoCarteira emissor, Cartao cartao) {
        this.id = id;
        this.email = email;
        this.cartao = cartao;
        this.associadaEm = LocalDateTime.now();
        this.emissor = emissor;
    }


    public CarteiraResponse domainToResponse() {
        return new CarteiraResponse(this.id, this.email, this.associadaEm, this.emissor);
    }
}
