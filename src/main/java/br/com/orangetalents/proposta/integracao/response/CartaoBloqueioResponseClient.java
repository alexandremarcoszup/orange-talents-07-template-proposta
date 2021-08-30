package br.com.orangetalents.proposta.integracao.response;

public class CartaoBloqueioResponseClient {

    private String resultado;

    public CartaoBloqueioResponseClient(){}

    public CartaoBloqueioResponseClient(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
