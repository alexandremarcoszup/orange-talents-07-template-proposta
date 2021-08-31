package br.com.orangetalents.proposta.controller.response;

import java.time.LocalDate;

public class AvisoResponse {

    private String id;

    private String destino;

    private LocalDate validoAte;

    public AvisoResponse(String id, String destino, LocalDate validoAte) {
        this.id = id;
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
