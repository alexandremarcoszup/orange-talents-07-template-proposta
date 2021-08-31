package br.com.orangetalents.proposta.service.impl;

import br.com.orangetalents.proposta.controller.request.CarteiraRequest;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.modelo.Carteira;
import br.com.orangetalents.proposta.integracao.CartaoWebClient;
import br.com.orangetalents.proposta.integracao.request.AssociaCarteiraRequest;
import br.com.orangetalents.proposta.integracao.response.AssociaCarteiraResponse;
import br.com.orangetalents.proposta.security.handler.EntityException;
import br.com.orangetalents.proposta.security.handler.IntegracaoException;
import br.com.orangetalents.proposta.service.AssociaCarteiraService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AssociaCarteiraServiceImpl implements AssociaCarteiraService {

    private final CartaoWebClient cartaoWebClient;
    public final Logger log = LoggerFactory.getLogger(BloqueiaCartaoServiceImpl.class);


    public AssociaCarteiraServiceImpl(CartaoWebClient cartaoWebClient) {
        this.cartaoWebClient = cartaoWebClient;
    }

    @Override
    public Carteira associaCarteira(Cartao cartao, CarteiraRequest request) {

        try {
            AssociaCarteiraRequest associaRequest = new AssociaCarteiraRequest(request.getEmail(), request.getCarteira());
            AssociaCarteiraResponse associaResponse = cartaoWebClient.associarCarteira(cartao.getId(), associaRequest);

            if (associaResponse.getResultado().equals("ASSOCIADA")) {
                Carteira carteira = request.requestToDomain(cartao, associaResponse.getId());
                cartao.adicionaCarteira(carteira);
                return carteira;
            } else {
                log.error("ALguma falha na associação de carteira.");
                throw new EntityException(associaResponse.getResultado(), "Nao foi possivel associar carteira.");
            }
        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
                log.error("Tentativa de associar outra carteira do mesmo tipo.");
                throw new EntityException("carteiras", "Nao foi possivel associar carteira já está associada");
            }
            log.error("Erro com o cliente Feign.");
            throw new IntegracaoException("Não foi possível comunicar o sistema legado para bloquear cartão", cartao.getId().substring(0, 4) + "****");
        }
    }
}
