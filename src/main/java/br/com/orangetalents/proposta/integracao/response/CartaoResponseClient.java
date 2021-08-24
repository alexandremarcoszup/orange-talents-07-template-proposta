package br.com.orangetalents.proposta.integracao.response;

import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.modelo.Proposta;
import br.com.orangetalents.proposta.domain.repository.PropostaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

public class CartaoResponseClient {


    private String idCartao;

    private LocalDateTime emissao;

    private String titular;

    private List<BloqueioResponseClient> bloqueios;

    private List<AvisoResponseClient> avisos;

    private List<CarteiraResponseClient> carteiras;

    private List<ParcelaResponseClient> parcelaResponsClients;

    private Integer limite;

    private RenegociacaoResponseClient renegociacao;

    private VencimentoResponseClient vencimento;

    private String idProposta;

    public CartaoResponseClient(String idCartao, LocalDateTime emissao, String titular, List<BloqueioResponseClient> bloqueios,
                                List<AvisoResponseClient> avisos, List<CarteiraResponseClient> carteiras, List<ParcelaResponseClient> parcelaResponsClients,
                                Integer limite, RenegociacaoResponseClient renegociacao, VencimentoResponseClient vencimento, String idProposta) {
        this.idCartao = idCartao;
        this.emissao = emissao;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelaResponsClients = parcelaResponsClients;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public Cartao responseClientToDomain(PropostaRepository propostaRepository) {

        Proposta proposta = propostaRepository.findById(parseLong(this.idProposta)).get();

        return new Cartao(this.idCartao, this.emissao, this.titular, this.bloqueios.stream().map(BloqueioResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.avisos.stream().map(AvisoResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.carteiras.stream().map(CarteiraResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.parcelaResponsClients.stream().map(ParcelaResponseClient::responseClientToDomain).collect(Collectors.toList()),
                this.limite, this.renegociacao.responseClientToDomain(), this.vencimento.responseClientToDomain(), proposta);
    }
}
