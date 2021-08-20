package br.com.orangetalents.proposta.controller;

import br.com.orangetalents.proposta.controller.request.PropostaRequest;
import br.com.orangetalents.proposta.modelo.domain.Proposta;
import br.com.orangetalents.proposta.modelo.repository.PropostaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final PropostaRepository propostaRepository;

    public PropostaController(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    @PostMapping
    public ResponseEntity<?> criaProposta(UriComponentsBuilder uriBuilder, @RequestBody @Valid PropostaRequest propostaRequest){

        Proposta proposta = propostaRepository.save(propostaRequest.toDomain());

        URI uri = uriBuilder.path("proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(proposta.toResponse());
    }
}
