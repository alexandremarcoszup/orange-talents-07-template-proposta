package br.com.orangetalents.proposta.controller.request;

import br.com.orangetalents.proposta.domain.modelo.Aviso;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    private String destino;

    @Future
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate validoAte;


    public AvisoViagemRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public Aviso requestToDomain(Cartao cartao){
        return new Aviso(this.validoAte, this.destino, cartao);
    }


}
