package br.com.orangetalents.proposta.controller.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class PropostaResponse {

    private Long id;

    private String cpfOrCnpj;

    private String email;

    private String endereco;

    private Integer salario;

    public PropostaResponse(Long id, String cpfOrCnpj, String email, String endereco, Integer salario) {
        this.id = id;
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
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
}
