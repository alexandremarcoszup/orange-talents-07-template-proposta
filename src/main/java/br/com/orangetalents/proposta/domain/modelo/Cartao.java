package br.com.orangetalents.proposta.domain.modelo;

import br.com.orangetalents.proposta.controller.response.CartaoBloqueadoResponse;
import br.com.orangetalents.proposta.domain.enums.CartaoStatus;
import br.com.orangetalents.proposta.security.handler.EntityException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.com.orangetalents.proposta.domain.enums.CartaoStatus.BLOQUEADO;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cartao {

    @Id
    @Column(insertable = true, updatable = true, nullable = false)
    private String id;

    private LocalDateTime emissao;

    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Bloqueio> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Aviso> avisos= new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Carteira> carteiras= new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<Parcela> parcelaRequests= new ArrayList<>();

    private Integer limite;

    @Embedded
    private Renegociacao renegociacao;

    @Embedded
    private Vencimento vencimento;

    @OneToOne
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<Biometria> biometrias = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private CartaoStatus status;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, LocalDateTime emissao, String titular, List<Bloqueio> bloqueios, List<Aviso> avisos,
                  List<Carteira> carteiras, List<Parcela> parcelaRequests, Integer limite, Proposta proposta, CartaoStatus status) {
        this.id = id;
        this.emissao = LocalDateTime.now();
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelaRequests = parcelaRequests;
        this.limite = limite;
        this.proposta = proposta;
        this.status = status;
    }


    public void setRenegociacao(Renegociacao renegociacao) {
        this.renegociacao = renegociacao;
    }

    public void setVencimento(Vencimento vencimento) {
        this.vencimento = vencimento;
    }


    public void addBiometria(Biometria biometria) {
        Assert.isTrue(!biometrias.contains(biometria), "Algo de errado não está certo, pois não foi inserido a biometria.");

        if (biometria != null)
            this.biometrias.add(biometria);

    }
    public void bloquear(String userAgent, String ipaddress){
        if(this.status == BLOQUEADO)
            throw new EntityException(this.status.toString(), "Cartão já está bloqueado");
        this.status = BLOQUEADO;
        bloqueios.add(new Bloqueio(userAgent, ipaddress, false, this));
    }

    public CartaoBloqueadoResponse domainToBloqueadoResponse(){
        return new CartaoBloqueadoResponse(this.id, this.emissao, this.titular, this.status);
    }
}
