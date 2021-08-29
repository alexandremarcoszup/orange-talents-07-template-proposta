package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Aviso {

    @Id
    @Column(insertable = true, updatable = true, nullable = false)
    private Long id;

    private LocalDate validoAte;

    private String destino;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }
}
