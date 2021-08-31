package br.com.orangetalents.proposta.service.impl;

import br.com.orangetalents.proposta.controller.request.AvisoViagemRequest;
import br.com.orangetalents.proposta.domain.modelo.Aviso;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.repository.AvisoRepository;
import br.com.orangetalents.proposta.service.AvisoViagemService;
import org.springframework.stereotype.Service;

@Service
public class AvisoViagemServiceImpl implements AvisoViagemService {

    private final AvisoRepository avisoRepository;

    public AvisoViagemServiceImpl(AvisoRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    @Override
    public Aviso criarAvisoDeViagem(Cartao cartao, AvisoViagemRequest request) {
        Aviso aviso = request.requestToDomain(cartao);
        cartao.adicionaAviso(aviso);
        return aviso;
    }
}
