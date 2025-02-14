package br.com.orangetalents.proposta.controller.request;

import br.com.orangetalents.proposta.integracao.request.ConsultaAnaliseRequest;
import br.com.orangetalents.proposta.domain.modelo.Proposta;
import br.com.orangetalents.proposta.security.validations.ExistsId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvaliaSolicitanteRequest {

    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @ExistsId(domainClass = Proposta.class, fieldName = "id")
    @NotNull
    private Long idProposta;

    public AvaliaSolicitanteRequest(@NotBlank String documento, @NotBlank String nome, @NotNull Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public ConsultaAnaliseRequest requestToAnalise() {
        return new ConsultaAnaliseRequest(this.documento, this.nome, this.idProposta.toString());
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
