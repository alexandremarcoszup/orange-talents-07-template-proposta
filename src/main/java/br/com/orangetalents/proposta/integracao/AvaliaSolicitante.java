package br.com.orangetalents.proposta.integracao;

import br.com.orangetalents.proposta.integracao.request.ConsultaAnalise;
import br.com.orangetalents.proposta.integracao.response.ResultadoAnalise;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "analise", url = "http://localhost:9999/api/solicitacao")
public interface AvaliaSolicitante {

    @RequestMapping(method = RequestMethod.GET)
    ResultadoAnalise analiseSolicitacao(ConsultaAnalise consultaAnalise);
}
