package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Bloqueio {

    @Id
    @Column(insertable = true, updatable = true, nullable = false)
    private String id;

    private LocalDateTime bloqueadoEm;

    private String sistemaResponsavel;

    private String ipCliente;

    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {

        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public Bloqueio(String userAgent, String ipCliente, boolean ativo, Cartao cartao) {
        this.id = UUID.randomUUID().toString();
        this.bloqueadoEm = LocalDateTime.now();
        this.sistemaResponsavel = userAgent;
        this.ipCliente = ipCliente;
        this.ativo = ativo;
        this.cartao = cartao;
    }

    public void setIpCliente(String ipCliente) {
        this.ipCliente = ipCliente;
    }
}
