package br.com.orangetalents.proposta.modelo.domain;

import br.com.orangetalents.proposta.controller.response.PropostaResponse;
import br.com.orangetalents.proposta.modelo.enums.SolicitacaoStatus;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private SolicitacaoStatus status;

    @NotNull
    @PositiveOrZero
    private Integer salario;

    @Deprecated
    public Proposta(){};

    public Proposta(String cpfOrCnpj, String email, String endereco, Integer salario, SolicitacaoStatus status) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public PropostaResponse toResponse() {

        return new PropostaResponse(this.id, this.cpfOrCnpj,this.email, this.endereco,this.salario,this.status);
    }

    public void setStatus(SolicitacaoStatus status) {
        this.status = status;
    }
}
