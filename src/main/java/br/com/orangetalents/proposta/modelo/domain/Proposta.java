package br.com.orangetalents.proposta.modelo.domain;

import br.com.orangetalents.proposta.controller.response.PropostaResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Proposta {

    @Id
    @GeneratedValue
    private Long id;

    private String cpfOrCnpj;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @NotNull
    @PositiveOrZero
    private Integer salario;

    public Proposta(String cpfOrCnpj, String email, String endereco, Integer salario) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public PropostaResponse toResponse() {

        return new PropostaResponse(this.id, this.cpfOrCnpj,this.email, this.endereco,this.salario);
    }
}
