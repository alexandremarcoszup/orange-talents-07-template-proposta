package br.com.orangetalents.proposta.security.handler;

public class AssociationCardException extends RuntimeException{

    private String message;
    private String idProposta;

    public AssociationCardException(String message, String idProposta) {
        this.message = message;
        this.idProposta = idProposta;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
