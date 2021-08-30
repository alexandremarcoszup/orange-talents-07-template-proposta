package br.com.orangetalents.proposta.service.impl;

import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.integracao.CartaoWebClient;
import br.com.orangetalents.proposta.integracao.request.BloqueioCartaoRequest;
import br.com.orangetalents.proposta.integracao.response.CartaoBloqueioResponseClient;
import br.com.orangetalents.proposta.security.handler.IntegracaoException;
import br.com.orangetalents.proposta.service.BloqueiaCartaoService;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.orangetalents.proposta.domain.enums.CartaoStatus.BLOQUEADO;

@Service
public class BloqueiaCartaoServiceImpl implements BloqueiaCartaoService {

    private final CartaoWebClient cartaoWebClient;
    static Logger log = Logger.getLogger("BloqueiaCartaoLogger");


    public BloqueiaCartaoServiceImpl(CartaoWebClient cartaoWebClient) {
        this.cartaoWebClient = cartaoWebClient;
    }

    @Override
    public void bloqueiaCartao(Cartao cartao, String userAgent, String ipaddress) {

        try {
            BloqueioCartaoRequest request = new BloqueioCartaoRequest(userAgent);


            CartaoBloqueioResponseClient response = cartaoWebClient.bloqueiaCartao(cartao.getId(),request);

            if (response.getResultado().equals(BLOQUEADO.toString())) {
                cartao.bloquear(userAgent, ipaddress);
                log.log(Level.INFO, "Bloqueando cartao");
            }
        } catch (FeignException e){
            log.log(Level.parse("ERROR"), "Erro ao bloquear cartão");
            throw new IntegracaoException("Não foi possível comunicar o sistema legado para bloquear cartão", cartao.getId().substring(4)+"****");
        }


    }
}
