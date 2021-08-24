package br.com.orangetalents.proposta.controller.request;

import br.com.orangetalents.proposta.domain.modelo.Proposta;
import br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus;
import br.com.orangetalents.proposta.security.validations.CPFOrCNPJ;
import br.com.orangetalents.proposta.security.validations.UniqueDocument;

import javax.validation.constraints.*;

public class PropostaRequest {

    @NotBlank
    @UniqueDocument(fieldName = "cpfOrCnpj", domainClass = Proposta.class)
    @CPFOrCNPJ
    private String cpfOrCnpj;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @PositiveOrZero
    private Integer salario;

    public PropostaRequest(String cpfOrCnpj, String email, String nome, String endereco, Integer salario) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toDomain() {
        return new Proposta(this.cpfOrCnpj, this.email, this.nome, this.endereco, this.salario, SolicitacaoStatus.EM_PROCESSAMENTO);
    }
}
