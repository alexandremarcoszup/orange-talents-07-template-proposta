package br.com.orangetalents.proposta.domain.modelo;

import br.com.orangetalents.proposta.controller.response.PropostaResponse;
import br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpfOrCnpj;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @Enumerated(EnumType.STRING)
    private SolicitacaoStatus status;

    @NotNull
    @PositiveOrZero
    private Integer salario;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String cpfOrCnpj, String email, String nome, String endereco, Integer salario, SolicitacaoStatus status) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.status = status;
    }

    public PropostaResponse toResponse() {

        return new PropostaResponse(this.id, this.cpfOrCnpj, this.email, this.nome, this.endereco, this.salario, this.status);
    }

    public void setStatus(SolicitacaoStatus status) {
        this.status = status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public String getNome() {
        return nome;
    }
}
