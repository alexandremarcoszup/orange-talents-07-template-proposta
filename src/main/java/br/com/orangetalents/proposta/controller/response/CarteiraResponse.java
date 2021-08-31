package br.com.orangetalents.proposta.controller.response;

import br.com.orangetalents.proposta.domain.enums.TipoCarteira;

import java.time.LocalDateTime;

public class CarteiraResponse {

    private String id;

    private String email;

    private LocalDateTime associadaEm;

    private TipoCarteira emissor;

    public CarteiraResponse(String id, String email, LocalDateTime associadaEm, TipoCarteira emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadaEm() {
        return associadaEm;
    }

    public TipoCarteira getEmissor() {
        return emissor;
    }
}
