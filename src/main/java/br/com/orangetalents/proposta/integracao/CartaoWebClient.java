package br.com.orangetalents.proposta.integracao;

import br.com.orangetalents.proposta.integracao.request.AvisoViagemCartaoRequest;
import br.com.orangetalents.proposta.integracao.request.BloqueioCartaoRequest;
import br.com.orangetalents.proposta.integracao.response.AvisoViagemCartaoResponse;
import br.com.orangetalents.proposta.integracao.response.CartaoBloqueioResponseClient;
import br.com.orangetalents.proposta.integracao.response.CartaoResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(name = "cartoes", url = "http://localhost:8888/api/cartoes")
public interface CartaoWebClient {

    @PostMapping(value = "/{id}/bloqueios", consumes = MediaType.APPLICATION_JSON_VALUE)
    CartaoBloqueioResponseClient bloqueiaCartao(@PathVariable("id") String idCartao, @RequestBody BloqueioCartaoRequest bloqueioCartaoRequest);

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    CartaoResponseClient recuperaCartao(@RequestParam String idProposta);

    @PostMapping(value = "/{id}/avisos", consumes = MediaType.APPLICATION_JSON_VALUE)
    AvisoViagemCartaoResponse avisoViagemCartao(@PathVariable("id") String idCartao, @RequestBody AvisoViagemCartaoRequest avisoViagemCartaoRequest);


}
