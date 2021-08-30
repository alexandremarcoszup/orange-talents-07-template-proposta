package br.com.orangetalents.proposta.controller;

import br.com.orangetalents.proposta.controller.response.CartaoBloqueadoResponse;
import br.com.orangetalents.proposta.domain.modelo.Biometria;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.repository.CartaoRepository;
import br.com.orangetalents.proposta.security.handler.EntityNotFound;
import br.com.orangetalents.proposta.service.BloqueiaCartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {

    private final CartaoRepository cartaoRepository;
    private final BloqueiaCartaoService bloqueiaCartaoService;

    static Logger log = Logger.getLogger("Controller de biometria");

    public CartaoController(CartaoRepository cartaoRepository, BloqueiaCartaoService bloqueiaCartaoService) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueiaCartaoService = bloqueiaCartaoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> adicionaBiometriaNoCartao(String biometriaBase64, @PathVariable("id") String idCartao,
                                                       UriComponentsBuilder uricomponentBulder) {


        Cartao cartao = findCartaoBydId(idCartao);

        log.log(Level.INFO, "Cadastrando biometria");

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
