package br.com.orangetalents.proposta.domain.modelo;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Aviso {

    private LocalDate validoAte;

    private String destino;

    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }
}
