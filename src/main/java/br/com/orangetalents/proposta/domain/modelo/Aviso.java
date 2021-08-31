package br.com.orangetalents.proposta.domain.modelo;

import br.com.orangetalents.proposta.controller.response.AvisoResponse;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Aviso {

    @Id
    private String id = UUID.randomUUID().toString();

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

    public Aviso(LocalDate validoAte, String destino, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.cartao = cartao;
    }

    public AvisoResponse domainToResponse(){
        return new AvisoResponse(this.id,this .destino, this.validoAte);
    }

}
