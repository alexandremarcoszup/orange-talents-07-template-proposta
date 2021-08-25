package br.com.orangetalents.proposta.security.handler;

public class EntityNotFound extends RuntimeException{

    private String message;

    private String className;

    public EntityNotFound(String message, String className) {
        this.message = message;
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public String getClassName() {
        return className;
    }
}
