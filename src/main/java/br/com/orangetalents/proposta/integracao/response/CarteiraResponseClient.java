package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.enums.TipoCarteira;
import br.com.orangetalents.proposta.domain.modelo.Carteira;

import java.time.LocalDateTime;

public class CarteiraResponseClient {

    private String id;

    private String email;

    private LocalDateTime associadaEm;

    private String emissor;

    public CarteiraResponseClient(String id, String email, LocalDateTime associadaEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadaEm = associadaEm;
        this.emissor = emissor;
    }

    public Carteira responseClientToDomain() {
        return new Carteira(this.id, this.email, TipoCarteira.valueOf(this.emissor));
    }

}
