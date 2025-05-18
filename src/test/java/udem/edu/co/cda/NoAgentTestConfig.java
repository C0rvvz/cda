package udem.edu.co.cda;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuración para desactivar agentes Java que pueden interferir con las pruebas
 */
@TestConfiguration
public class NoAgentTestConfig {

    static {
        System.setProperty("spring.test.mockito.mock-maker-inline", "true");
        System.setProperty("mockito.mock-maker-inline", "true");
    }

    @PostConstruct
    public void init() {
        // Configuraciones adicionales para las pruebas
        System.setProperty("mockito.mock-maker-inline", "true");
        System.setProperty("MOCKITO_MOCK_MAKER", "inline");
    }

    @Bean
    public String noAgentBean() {
        return "noAgentConfigured";
    }
}
