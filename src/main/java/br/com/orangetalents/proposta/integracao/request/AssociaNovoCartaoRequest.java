package br.com.orangetalents.proposta.integracao.request;

import br.com.orangetalents.proposta.domain.modelo.Proposta;

import java.io.Serializable;

public class AssociaNovoCartaoRequest implements Serializable {

    private String documento;

    private String nome;

    private String idProposta;

    public AssociaNovoCartaoRequest(Proposta proposta) {
        this.documento = proposta.getCpfOrCnpj();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
