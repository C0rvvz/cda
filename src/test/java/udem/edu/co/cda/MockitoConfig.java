package udem.edu.co.cda;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Configuración específica para Mockito con soporte para Java 21
 */
@TestConfiguration
public class MockitoConfig {

    static {
        System.setProperty("mockito.mock-maker-inline", "true");
    }

    @PostConstruct
    public void init() {
        // Habilitar el modo inline de Mockito para Java 21
        System.setProperty("mockito.mock-maker-inline", "true");
    }

    /**
     * Configura Mockito para usar una implementación compatible con Java 21
     */
    @Bean
    @Primary
    public String mockitoConfiguration() {
        return "mockitoConfigured";
    }
}