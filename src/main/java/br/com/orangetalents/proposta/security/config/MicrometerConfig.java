package br.com.orangetalents.proposta.security.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MicrometerConfig {

    private final MeterRegistry meterRegistry;

    public MicrometerConfig(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        meuContador();
    }

    public void meuContador() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissor", "Proposta - CONTADOR"));

        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);
        contadorDePropostasCriadas.increment();
    }

    public void contadorTimer(){
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissor", "Proposta - TIMER CRIADO"));

        Timer timerConsultarProposta = this.meterRegistry.timer("consultar_proposta", tags);
        timerConsultarProposta.record(() -> {
            // Método da sua operação
            //consultarProposta();
        });

    }


}
