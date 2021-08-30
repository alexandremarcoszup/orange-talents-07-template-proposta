package br.com.orangetalents.proposta.service;

import br.com.orangetalents.proposta.domain.modelo.Cartao;

public interface BloqueiaCartaoService {

    void bloqueiaCartao(Cartao cartao, String userAgent, String ipaddress);
}
