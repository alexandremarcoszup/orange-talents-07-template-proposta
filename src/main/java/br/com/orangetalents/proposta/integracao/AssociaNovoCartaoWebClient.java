package br.com.orangetalents.proposta.integracao;

import br.com.orangetalents.proposta.integracao.request.AssociaNovoCartaoRequest;
import br.com.orangetalents.proposta.integracao.response.CartaoResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface AssociaNovoCartaoWebClient {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    CartaoResponseClient associaCartao(AssociaNovoCartaoRequest associaNovoCartaoRequest);
}
