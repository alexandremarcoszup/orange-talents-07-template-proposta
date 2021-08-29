package br.com.orangetalents.proposta.service.impl;

import br.com.orangetalents.proposta.domain.modelo.Proposta;
import br.com.orangetalents.proposta.domain.repository.PropostaRepository;
import br.com.orangetalents.proposta.integracao.AssociaNovoCartaoWebClient;
import br.com.orangetalents.proposta.integracao.request.AssociaNovoCartaoRequest;
import br.com.orangetalents.proposta.integracao.response.CartaoResponseClient;
import br.com.orangetalents.proposta.security.handler.AssociationCardException;
import br.com.orangetalents.proposta.service.AssociaCartaoService;
import feign.FeignException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.orangetalents.proposta.domain.enums.SolicitacaoStatus.ELEGIVEL;

@Service
public class AssociaCartaoServiceImpl implements AssociaCartaoService {

    private final PropostaRepository propostaRepository;
    private final AssociaNovoCartaoWebClient associaNovoCartaoWebClient;
    static Logger log = Logger.getLogger("AssociaCartaoLogger");

    public AssociaCartaoServiceImpl(PropostaRepository propostaRepository, AssociaNovoCartaoWebClient associaNovoCartaoWebClient) {
        this.propostaRepository = propostaRepository;
        this.associaNovoCartaoWebClient = associaNovoCartaoWebClient;
    }

    @Override
    @Scheduled(fixedDelayString = "${schedule.service.config.fixeDelay}")
    public void associa() {

        List<Proposta> propostasElegiveis = propostaRepository.findAllByStatus(ELEGIVEL);

        if (!propostasElegiveis.isEmpty())
            for (Proposta proposta : propostasElegiveis) {
                AssociaNovoCartaoRequest request = new AssociaNovoCartaoRequest(proposta);
                try{
                    CartaoResponseClient resposta = associaNovoCartaoWebClient.associaCartao(request);
                    log.log(Level.INFO,"Cartão pego");

                    resposta.responseClientToDomain(proposta);

                    propostaRepository.save(proposta);
                    log.log(Level.INFO,"Cartão salvo");
                }catch (FeignException e){
                    log.log(Level.parse("ERROR"), "Erro com o cliente Feign.");
                    throw new AssociationCardException("Erro de associacao de cartão.", request.getIdProposta());
                }
            }
    }
}
