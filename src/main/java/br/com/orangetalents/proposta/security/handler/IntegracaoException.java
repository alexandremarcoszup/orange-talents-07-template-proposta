package br.com.orangetalents.proposta.security.handler;

public class IntegracaoException extends RuntimeException{

    private String message;
    private String atribute;

    public IntegracaoException(String message, String atribute) {
        this.message = message;
        this.atribute = atribute;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getAtribute() {
        return atribute;
    }
}
