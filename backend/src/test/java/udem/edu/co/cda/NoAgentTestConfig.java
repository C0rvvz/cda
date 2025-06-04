package udem.edu.co.cda;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class NoAgentTestConfig {

    static {
        // Configura Mockito para usar el modo inline (compatible con Java 21+)
        System.setProperty("mockito.mock-maker-inline", "true");
    }

    @PostConstruct
    public void init() {
        // Reafirma la configuración en runtime (opcional)
        System.setProperty("mockito.mock-maker-inline", "true");
    }
}
