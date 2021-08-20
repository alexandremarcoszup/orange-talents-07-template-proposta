package br.com.orangetalents.proposta.security.handler;

public class DuplicatedDocumentException extends RuntimeException{

    private String campo;

    private String erro;

    public DuplicatedDocumentException(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
