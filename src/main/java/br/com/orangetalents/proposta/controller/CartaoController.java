package br.com.orangetalents.proposta.controller;

import br.com.orangetalents.proposta.controller.request.AvisoViagemRequest;
import br.com.orangetalents.proposta.controller.request.CarteiraRequest;
import br.com.orangetalents.proposta.controller.response.AvisoResponse;
import br.com.orangetalents.proposta.controller.response.CartaoBloqueadoResponse;
import br.com.orangetalents.proposta.domain.modelo.Aviso;
import br.com.orangetalents.proposta.domain.modelo.Biometria;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.modelo.Carteira;
import br.com.orangetalents.proposta.domain.repository.CartaoRepository;
import br.com.orangetalents.proposta.security.handler.EntityNotFound;
import br.com.orangetalents.proposta.service.AssociaCarteiraService;
import br.com.orangetalents.proposta.service.AvisoViagemService;
import br.com.orangetalents.proposta.service.BloqueiaCartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Base64;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {

    private final CartaoRepository cartaoRepository;
    private final BloqueiaCartaoService bloqueiaCartaoService;
    private final AvisoViagemService avisoViagemService;
    private final AssociaCarteiraService associaCarteiraService;

    public final Logger log = LoggerFactory.getLogger(CartaoController.class);


    public CartaoController(CartaoRepository cartaoRepository, BloqueiaCartaoService bloqueiaCartaoService, AvisoViagemService avisoViagemService, AssociaCarteiraService associaCarteiraService) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueiaCartaoService = bloqueiaCartaoService;
        this.avisoViagemService = avisoViagemService;
        this.associaCarteiraService = associaCarteiraService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> adicionaBiometriaNoCartao(String biometriaBase64, @PathVariable("id") String idCartao,
                                                       UriComponentsBuilder uricomponentBulder) {


        Cartao cartao = findCartaoBydId(idCartao);

        log.info("Cadastrando biometria");

        Biometria biometria = buildBiometria(biometriaBase64, cartao);
        cartao.addBiometria(biometria);
        cartaoRepository.save(cartao);

        URI uri = uricomponentBulder.buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/bloqueio/{id}")
    public ResponseEntity<CartaoBloqueadoResponse> bloqueiaCartao(@RequestHeader("User-Agent") String userAgent,
                                                                  @RequestHeader("ipaddress") String ipaddress,
                                                                  @PathVariable("id") String idCartao) {

        Cartao cartao = findCartaoBydId(idCartao);

        bloqueiaCartaoService.bloqueiaCartao(cartao, userAgent, ipaddress);
        cartaoRepository.save(cartao);

        return ResponseEntity.ok(cartao.domainToBloqueadoResponse());
    }

    @PostMapping("/aviso/{id}")
    public ResponseEntity<AvisoResponse> postaAvisoDeviagem(@RequestHeader("User-Agent") String userAgent,
                                                       @RequestHeader("ipaddress") String ipAddress,
                                                       @PathVariable("id") String idCartao,
                                                       @RequestBody @Valid AvisoViagemRequest avisoViagemRequest) {

        Cartao cartao = findCartaoBydId(idCartao);
        Aviso aviso = avisoViagemService.criarAvisoDeViagem(cartao, avisoViagemRequest, userAgent, ipAddress);
        cartaoRepository.save(cartao);

        return ResponseEntity.ok(aviso.domainToResponse());
    }

    @PostMapping("/carteira/{id}")
    public ResponseEntity<?> associaCarteira(UriComponentsBuilder uriComponentsBuilder,
                                             @RequestBody CarteiraRequest carteiraRequest,
                                             @PathVariable("id") String idCartao){

        Cartao cartao = findCartaoBydId(idCartao);
        Carteira carteira = associaCarteiraService.associaCarteira(cartao, carteiraRequest);
        cartaoRepository.save(cartao);
        URI uri = uriComponentsBuilder.buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(carteira.domainToResponse());
    }

    private Cartao findCartaoBydId(String idCartao) {
        return cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new EntityNotFound("Não foi encontrado o cartao.", this.getClass().toString()));
    }

    private Biometria buildBiometria(String base64, Cartao cartao) {
        byte[] biometriaDecoded = Base64.getDecoder().decode(base64);
        String biometria = new String(biometriaDecoded);

        return new Biometria(biometria, cartao);
    }
}
