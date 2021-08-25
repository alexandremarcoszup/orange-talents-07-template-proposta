package br.com.orangetalents.proposta.security.handler;

public class ErroDeBuscaDTO {

    private String className;

    private String message;

    public ErroDeBuscaDTO(String className, String message) {
        this.className = className;
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }
}
