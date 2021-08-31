package br.com.orangetalents.proposta.integracao.response;

public class AvisoViagemCartaoResponse {

    private String resultado;

    public AvisoViagemCartaoResponse(){}

    public AvisoViagemCartaoResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
