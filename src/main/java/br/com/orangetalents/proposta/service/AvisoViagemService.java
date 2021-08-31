package br.com.orangetalents.proposta.service;

import br.com.orangetalents.proposta.controller.request.AvisoViagemRequest;
import br.com.orangetalents.proposta.domain.modelo.Aviso;
import br.com.orangetalents.proposta.domain.modelo.Cartao;

public interface AvisoViagemService {

    Aviso criarAvisoDeViagem(Cartao cartao, AvisoViagemRequest request, String userAgent, String ipaddress);
}
