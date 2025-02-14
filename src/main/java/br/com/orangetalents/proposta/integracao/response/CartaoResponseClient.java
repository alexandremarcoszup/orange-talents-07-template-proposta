package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.modelo.Proposta;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.orangetalents.proposta.domain.enums.CartaoStatus.ATIVO;
import static br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus.FINALIZADO;

public class CartaoResponseClient {


    private String id;

    private LocalDateTime emissao;

    private String titular;

    private List<BloqueioResponseClient> bloqueios;

    private List<AvisoResponseClient> avisos;

    private List<CarteiraResponseClient> carteiras;

    private List<ParcelaResponseClient> parcelas;

    private Integer limite;

    private RenegociacaoResponseClient renegociacao;

    private VencimentoResponseClient vencimento;

    private String idProposta;

    public CartaoResponseClient(String id, LocalDateTime emissao, String titular, List<BloqueioResponseClient> bloqueios,
                                List<AvisoResponseClient> avisos, List<CarteiraResponseClient> carteiras, List<ParcelaResponseClient> parcelas,
                                Integer limite, RenegociacaoResponseClient renegociacao, VencimentoResponseClient vencimento, String idProposta) {
        this.id = id;
        if (id == null)
            this.id = UUID.randomUUID().toString();
        this.emissao = emissao;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Cartao responseClientToDomain(Proposta proposta) {


        Cartao cartao =  new Cartao(this.id, this.emissao, this.titular, this.bloqueios.stream().map(BloqueioResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.avisos.stream().map(AvisoResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.carteiras.stream().map(CarteiraResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.parcelas.stream().map(ParcelaResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.limite, proposta, ATIVO);

        if (renegociacao != null)
            cartao.setRenegociacao(this.renegociacao.responseClientToDomain());

        if (vencimento != null)
            cartao.setVencimento(this.vencimento.responseClientToDomain());


        proposta.setCartao(cartao);
        proposta.setStatus(FINALIZADO);

        return cartao;
    }

    public String getId() {
        return id;
    }
}
