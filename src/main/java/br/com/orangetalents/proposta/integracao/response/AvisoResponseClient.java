package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.modelo.Aviso;

import java.time.LocalDate;

public class AvisoResponseClient {

    private LocalDate validoAte;

    private String destino;

    public AvisoResponseClient(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso responseClientToDomain() {
        return new Aviso(this.validoAte, this.destino);
    }
}
