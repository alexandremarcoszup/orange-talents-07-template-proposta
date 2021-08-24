package br.com.orangetalents.proposta.controller.response;

import br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus;

public class PropostaResponse {

    private Long id;

    private String cpfOrCnpj;

    private String email;

    private String nome;

    private String endereco;

    private Integer salario;

    private SolicitacaoStatus status;

    public PropostaResponse(Long id, String cpfOrCnpj, String email, String nome, String endereco, Integer salario, SolicitacaoStatus status) {
        this.id = id;
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Integer getSalario() {
        return salario;
    }

    public SolicitacaoStatus getStatus() {
        return status;
    }
}
