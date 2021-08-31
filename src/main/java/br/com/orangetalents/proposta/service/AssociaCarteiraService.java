package br.com.orangetalents.proposta.service;

import br.com.orangetalents.proposta.controller.request.CarteiraRequest;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.modelo.Carteira;

public interface AssociaCarteiraService {
    Carteira associaCarteira(Cartao cartao, CarteiraRequest request);
}
