package br.com.orangetalents.proposta.domain.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cartao {

    @Id
    @Column(insertable = true, updatable = true, nullable = false)
    private String id;

    private LocalDateTime emissao;

    private String titular;

    @Embedded
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @Embedded
    private List<Aviso> avisos = new ArrayList<>();

    @Embedded
    private List<Carteira> carteiras = new ArrayList<>();

    @Embedded
    private List<Parcela> parcelaRequests = new ArrayList<>();

    private Integer limite;

    @Embedded
    private Renegociacao renegociacao;

    @Embedded
    private Vencimento vencimento;

    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Biometria> biometrias = new HashSet<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emissao, String titular, List<Bloqueio> bloqueios, List<Aviso> avisos,
                  List<Carteira> carteiras, List<Parcela> parcelaRequests, Integer limite, Proposta proposta) {
        this.id = id;
        this.emissao = emissao;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelaRequests = parcelaRequests;
        this.limite = limite;
        this.proposta = proposta;
    }


    public void setRenegociacao(Renegociacao renegociacao) {
        this.renegociacao = renegociacao;
    }

    public void setVencimento(Vencimento vencimento) {
        this.vencimento = vencimento;
    }

    public void addBiometria(Biometria biometria){
        Assert.isTrue(!biometrias.contains(biometria), "Algo de errado não está certo, pois não foi inserido a biometria.");

        if (biometria != null)
            this.biometrias.add(biometria);


    }
}
