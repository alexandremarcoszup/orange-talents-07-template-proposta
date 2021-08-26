package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.*;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fingerPrint;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria(){}

    public Biometria( String fingerPrint, Cartao cartao) {
        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
