package br.com.orangetalents.proposta.security.handler;

public class ErroDeIntegracaoDTO {

    private String message;
    private String entityId;

    public ErroDeIntegracaoDTO(String message, String entityId) {
        this.message = message;
        this.entityId = entityId;
    }

    public String getMessage() {
        return message;
    }

    public String getEntityId() {
        return entityId;
    }
}
