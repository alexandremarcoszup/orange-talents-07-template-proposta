package br.com.orangetalents.proposta.domain.modelo;

import br.com.orangetalents.proposta.controller.response.AvisoResponse;
import br.com.orangetalents.proposta.integracao.request.AvisoViagemCartaoRequest;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Aviso {

    @Id
    private String id = UUID.randomUUID().toString();

    private LocalDate validoAte;

    private String destino;

    private String userAgent;

    private String ipAddress;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Aviso() {
    }

    public Aviso(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public Aviso(LocalDate validoAte, String destino, String userAgent, String ipAddress, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.cartao = cartao;
    }

    public AvisoResponse domainToResponse(){
        return new AvisoResponse(this.id,this .destino, this.validoAte);
    }

    public AvisoViagemCartaoRequest domainToAvisoViagemCartaoRequest(){
        return new AvisoViagemCartaoRequest(this.destino, this.validoAte);
    }

    public String getId() {
        return id;
    }
}
