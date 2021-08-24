package br.com.orangetalents.proposta.service.impl;

import br.com.orangetalents.proposta.controller.request.AvaliaSolicitanteRequest;
import br.com.orangetalents.proposta.integracao.AvaliaSolicitanteWebClient;
import br.com.orangetalents.proposta.domain.modelo.Proposta;
import br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus;
import br.com.orangetalents.proposta.service.AvaliacaoPropostaService;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoPropostaServiceImpl implements AvaliacaoPropostaService {

    private final AvaliaSolicitanteWebClient avaliaSolicitanteWebClient;

    public AvaliacaoPropostaServiceImpl(AvaliaSolicitanteWebClient avaliaSolicitanteWebClient) {
        this.avaliaSolicitanteWebClient = avaliaSolicitanteWebClient;
    }

    @Override
    public Proposta avaliar(AvaliaSolicitanteRequest avaliaSolicitanteRequest, Proposta proposta) {

        try {
            var resultadoAnalise = avaliaSolicitanteWebClient.analiseSolicitacao(avaliaSolicitanteRequest.requestToAnalise());
            if (resultadoAnalise.getResultadoSolicitacao().equals("SEM_RESTRICAO"))
                proposta.setStatus(SolicitacaoStatus.ELEGIVEL);
            else
                proposta.setStatus(SolicitacaoStatus.NAO_ELEGIVEL);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                proposta.setStatus(SolicitacaoStatus.NAO_ELEGIVEL);
            }
        }

        return proposta;

    }
}
