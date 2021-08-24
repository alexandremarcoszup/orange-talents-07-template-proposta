package br.com.orangetalents.proposta.domain.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cartao {

    @Id
    @Column(insertable = true, updatable = true, nullable = false)
    private String id;

    private LocalDateTime emissao;

    private String titular;

    @Embedded
    private List<Bloqueio> bloqueios;

    @Embedded
    private List<Aviso> avisos;

    @Embedded
    private List<Carteira> carteiras;

    @Embedded
    private List<Parcela> parcelaRequests;

    private Integer limite;

    @Embedded
    private Renegociacao renegociacao;

    @Embedded
    private Vencimento vencimento;

    @OneToOne
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emissao, String titular, List<Bloqueio> bloqueios, List<Aviso> avisos,
                  List<Carteira> carteiras, List<Parcela> parcelaRequests, Integer limite, Renegociacao renegociacao,
                  Vencimento vencimento, Proposta proposta) {
        this.id = id;
        this.emissao = emissao;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelaRequests = parcelaRequests;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.proposta = proposta;
    }
}
