package br.com.orangetalents.proposta.integracao.request;

import javax.validation.constraints.NotBlank;

public class ConsultaAnalise {

    private String documento;
    private String nome;
    private String idProposta;

    public ConsultaAnalise(@NotBlank String documento, @NotBlank String nome, @NotBlank String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
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
