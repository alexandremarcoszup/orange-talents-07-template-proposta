package br.com.orangetalents.proposta.controller.request;

import br.com.orangetalents.proposta.modelo.domain.Proposta;
import br.com.orangetalents.proposta.security.validations.CPFOrCNPJ;

import javax.validation.constraints.*;

public class PropostaRequest {

    @NotBlank
    @CPFOrCNPJ
    private String cpfOrCnpj;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @NotNull
    @PositiveOrZero
    private Integer salario;

    public PropostaRequest(String cpfOrCnpj, String email, String endereco, Integer salario) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toDomain() {
        return new Proposta(this.cpfOrCnpj, this.email, this.endereco, this.salario);
    }
}
