package br.com.orangetalents.proposta.service;

import br.com.orangetalents.proposta.controller.request.AvaliaSolicitanteRequest;
import br.com.orangetalents.proposta.domain.modelo.Proposta;

public interface AvaliacaoPropostaService {

    Proposta avaliar(AvaliaSolicitanteRequest avaliaSolicitanteRequest, Proposta proposta);
}
