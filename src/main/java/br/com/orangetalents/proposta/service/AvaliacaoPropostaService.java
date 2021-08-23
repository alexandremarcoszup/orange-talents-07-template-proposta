package br.com.orangetalents.proposta.service;

import br.com.orangetalents.proposta.controller.request.AvaliaSolicitanteRequest;
import br.com.orangetalents.proposta.integracao.AvaliaSolicitante;
import br.com.orangetalents.proposta.modelo.domain.Proposta;
import br.com.orangetalents.proposta.modelo.enums.SolicitacaoStatus;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoPropostaService {

    private final AvaliaSolicitante avaliaSolicitante;

    public AvaliacaoPropostaService(AvaliaSolicitante avaliaSolicitante) {
        this.avaliaSolicitante = avaliaSolicitante;
    }

    public Proposta avaliar(AvaliaSolicitanteRequest avaliaSolicitanteRequest, Proposta proposta) {

        try {
            var resultadoAnalise = avaliaSolicitante.analiseSolicitacao(avaliaSolicitanteRequest.requestToAnalise());
            if (resultadoAnalise.getResultadoSolicitacao().equals("SEM_RESTRICAO"))
                proposta.setStatus(SolicitacaoStatus.ELEGIVEL);
            else
                proposta.setStatus(SolicitacaoStatus.NAO_ELEGIVEL);
        } catch (FeignException e){
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()){
                proposta.setStatus(SolicitacaoStatus.NAO_ELEGIVEL);
            }
        }

        return proposta;

    }
}
