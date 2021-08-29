package br.com.orangetalents.proposta.controller.response;

import br.com.orangetalents.proposta.domain.enums.CartaoStatus;

import java.time.LocalDateTime;

public class CartaoBloqueadoResponse {

    private String id;

    private LocalDateTime emissao;

    private String titular;

    private CartaoStatus status;

    public CartaoBloqueadoResponse(String id, LocalDateTime emissao, String titular, CartaoStatus status) {
        this.id = id;
        this.emissao = emissao;
        this.titular = titular;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmissao() {
        return emissao;
    }

    public String getTitular() {
        return titular;
    }

    public CartaoStatus getStatus() {
        return status;
    }
}
