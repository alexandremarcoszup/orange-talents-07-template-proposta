package br.com.orangetalents.proposta.integracao.request;

import br.com.orangetalents.proposta.domain.enums.TipoCarteira;

public class AssociaCarteiraRequest {

    private String email;

    private String carteira;

    public AssociaCarteiraRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
