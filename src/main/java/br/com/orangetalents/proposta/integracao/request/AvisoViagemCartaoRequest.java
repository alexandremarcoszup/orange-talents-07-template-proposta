package br.com.orangetalents.proposta.integracao.request;

import java.time.LocalDate;

public class AvisoViagemCartaoRequest {

    private String destino;

    private LocalDate validoAte;

    public AvisoViagemCartaoRequest(){}

    public AvisoViagemCartaoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
