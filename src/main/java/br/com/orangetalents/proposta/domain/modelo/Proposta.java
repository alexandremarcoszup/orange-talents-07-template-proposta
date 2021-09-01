package br.com.orangetalents.proposta.domain.modelo;

import br.com.orangetalents.proposta.controller.response.PropostaResponse;
import br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus;
import br.com.orangetalents.proposta.security.config.JasyptConfig;

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

    @Column(unique = true, nullable = false)
    private String cpfOrCnpj;

    @Column(unique = true, nullable = false)
    private String hashCpfOrCnpj;

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
        this.cpfOrCnpj = criptografa(cpfOrCnpj);
        this.hashCpfOrCnpj = hasheia(cpfOrCnpj);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.status = status;
    }

    public PropostaResponse toResponse() {

        return new PropostaResponse(this.id, descriptografa(this.cpfOrCnpj), this.email, this.nome, this.endereco, this.salario, this.status);
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

    public String getCpfOrCnpjDescriptografado() {
        return descriptografa(cpfOrCnpj);
    }

    public String getNome() {
        return nome;
    }

    private String hasheia(String documento) {
        return new JasyptConfig().gerarHash(documento);
    }

    public String criptografa(String documento) {
        return new JasyptConfig().criptografar(documento);
    }

    private String descriptografa(String documento) {
        return new JasyptConfig().descriptografar(documento);
    }
}
