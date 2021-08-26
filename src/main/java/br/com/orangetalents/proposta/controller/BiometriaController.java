package br.com.orangetalents.proposta.controller;

import br.com.orangetalents.proposta.domain.modelo.Biometria;
import br.com.orangetalents.proposta.domain.modelo.Cartao;
import br.com.orangetalents.proposta.domain.repository.CartaoRepository;
import br.com.orangetalents.proposta.security.handler.EntityNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/biometria")
public class BiometriaController {

    private final CartaoRepository cartaoRepository;

    static Logger log = Logger.getLogger("Controller de biometria");

    public BiometriaController(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> adicionaBiometriaNoCartao(String biometriaBase64, @PathVariable("idCartao") String idCartao,
                                                       UriComponentsBuilder uricomponentBulder) {

        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);

        if (cartao.isPresent()) {

            log.log(Level.INFO, "Cadastrando biometria");

            Biometria biometria = buildBiometria(biometriaBase64, cartao.get());
            cartao.get().addBiometria(biometria);
            cartaoRepository.save(cartao.get());

            URI uri = uricomponentBulder.buildAndExpand(biometria.getId()).toUri();

            return ResponseEntity.created(uri).build();
        } else {
            throw new EntityNotFound("Não foi encontrado o cartao.", cartao.getClass().toString());
        }
    }

    private Biometria buildBiometria(String base64, Cartao cartao) {
        byte[] biometriaDecoded = Base64.getDecoder().decode(base64);
        String biometria = new String(biometriaDecoded);

        return new Biometria(biometria, cartao);
    }
}
