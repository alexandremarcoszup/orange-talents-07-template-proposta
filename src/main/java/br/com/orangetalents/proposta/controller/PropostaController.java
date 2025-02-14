package br.com.orangetalents.proposta.controller;

import br.com.orangetalents.proposta.controller.request.AvaliaSolicitanteRequest;
import br.com.orangetalents.proposta.controller.request.PropostaRequest;
import br.com.orangetalents.proposta.controller.response.PropostaResponse;
import br.com.orangetalents.proposta.domain.modelo.Proposta;
import br.com.orangetalents.proposta.domain.repository.PropostaRepository;
import br.com.orangetalents.proposta.security.handler.EntityNotFound;
import br.com.orangetalents.proposta.service.AvaliacaoPropostaService;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final PropostaRepository propostaRepository;
    private final AvaliacaoPropostaService avaliacaoPropostaService;
    private final Tracer tracer;

    public PropostaController(PropostaRepository propostaRepository, AvaliacaoPropostaService avaliacaoPropostaService, Tracer tracer) {
        this.propostaRepository = propostaRepository;
        this.avaliacaoPropostaService = avaliacaoPropostaService;
        this.tracer = tracer;
    }

    @PostMapping
    public ResponseEntity<PropostaResponse> criaProposta(UriComponentsBuilder uriBuilder, @RequestBody @Valid PropostaRequest propostaRequest) {

        Proposta proposta = propostaRepository.save(propostaRequest.toDomain());
        Span activeSpan = tracer.activeSpan();
        activeSpan.log("Aqui jás a criação de uma proposta.");

        activeSpan.setTag("operation.status", "success");
        activeSpan.setBaggageItem("proposta.id", proposta.getId().toString());

        URI uri = uriBuilder.path("proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(proposta.toResponse());
    }

    @GetMapping
    public ResponseEntity<PropostaResponse> avaliaSolicitante(@RequestBody @Valid AvaliaSolicitanteRequest avaliaSolicitanteRequest) {

        Proposta proposta = findProposta(avaliaSolicitanteRequest.getIdProposta());

        avaliacaoPropostaService.avaliar(avaliaSolicitanteRequest, proposta);
        propostaRepository.save(proposta);

        return ResponseEntity.ok(proposta.toResponse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> acompanhar(@PathVariable("id") Long idProposta) {
        return ResponseEntity.ok(findProposta(idProposta).toResponse());
    }

    private Proposta findProposta(Long id) {

        Optional<Proposta> proposta = propostaRepository.findById(id);

        return proposta.orElseThrow(() -> new EntityNotFound("Entidade não encontrada de id: " + id + " não achado", Proposta.class.getName()));
    }
}
