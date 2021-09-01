package br.com.orangetalents.proposta.service.impl;

import br.com.orangetalents.proposta.controller.request.AvisoViagemRequest;
import br.com.orangetalents.proposta.domain.modelo.Aviso;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.repository.AvisoRepository;
import br.com.orangetalents.proposta.integracao.CartaoWebClient;
import br.com.orangetalents.proposta.security.handler.EntityException;
import br.com.orangetalents.proposta.security.handler.IntegracaoException;
import br.com.orangetalents.proposta.service.AvisoViagemService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AvisoViagemServiceImpl implements AvisoViagemService {

    private final AvisoRepository avisoRepository;
    private final CartaoWebClient cartaoWebClient;
    public final Logger log = LoggerFactory.getLogger(Service.class);


    public AvisoViagemServiceImpl(AvisoRepository avisoRepository, CartaoWebClient cartaoWebClient) {
        this.avisoRepository = avisoRepository;
        this.cartaoWebClient = cartaoWebClient;
    }

    @Override
    public Aviso criarAvisoDeViagem(Cartao cartao, AvisoViagemRequest request, String userAgent, String ipAddress) {

        try {

            Aviso aviso = request.requestToDomain(cartao, userAgent, ipAddress);
            cartao.adicionaAviso(aviso);

            log.info("Avisando sistema legado");

            var resultado = cartaoWebClient.avisoViagemCartao(cartao.getId(), aviso.domainToAvisoViagemCartaoRequest());
            if (resultado.getResultado().equals("CRIADO"))
                return aviso;
            else
                throw new EntityException("avisos", "O Aviso já foi feito no sistema legado");
        } catch (FeignException e) {
            log.error("Erro ao bloquear cartão");
            throw new IntegracaoException("Não foi possível comunicar o sistema legado para fazer o aviso de viagem cartão. Id do aviso: ", cartao.getId().substring(4) + "****");
        }
    }
}
