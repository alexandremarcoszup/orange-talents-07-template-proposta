package br.com.orangetalents.proposta.service.impl;

import br.com.orangetalents.proposta.domain.modelo.Proposta;
import br.com.orangetalents.proposta.domain.repository.PropostaRepository;
import br.com.orangetalents.proposta.integracao.CartaoWebClient;
import br.com.orangetalents.proposta.integracao.request.AssociaNovoCartaoRequest;
import br.com.orangetalents.proposta.integracao.response.CartaoResponseClient;
import br.com.orangetalents.proposta.security.handler.IntegracaoException;
import br.com.orangetalents.proposta.service.AssociaCartaoService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus.ELEGIVEL;

@Service
public class AssociaCartaoServiceImpl implements AssociaCartaoService {

    private final PropostaRepository propostaRepository;
    private final CartaoWebClient cartaoWebClient;
    public final Logger log = LoggerFactory.getLogger(AssociaCartaoServiceImpl.class);


    public AssociaCartaoServiceImpl(PropostaRepository propostaRepository, CartaoWebClient cartaoWebClient) {
        this.propostaRepository = propostaRepository;
        this.cartaoWebClient = cartaoWebClient;
    }

    @Override
    @Scheduled(fixedDelayString = "${schedule.service.config.fixeDelay}")
    public void associa() {

        List<Proposta> propostasElegiveis = propostaRepository.findAllByStatus(ELEGIVEL);

        if (!propostasElegiveis.isEmpty())
            for (Proposta proposta : propostasElegiveis) {
                AssociaNovoCartaoRequest request = new AssociaNovoCartaoRequest(proposta);
                try{
                    CartaoResponseClient resposta = cartaoWebClient.recuperaCartao(request.getIdProposta());
                    log.info("Cartão pego");
                    resposta.responseClientToDomain(proposta);

                    propostaRepository.save(proposta);
                    log.info("Cartão salvo");
                }catch (FeignException e){
                    if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value())
                        log.error("Partao ja associado a prosta.");
                    log.error("Erro com o cliente Feign.");
                    throw new IntegracaoException("Erro de associacao de cartão.", request.getIdProposta());
                }
            }
    }
}
