package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Vencimento {

    @Column(name = "id_vencimento")

    private String id;

    private Integer dia;

    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
