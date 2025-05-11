package udem.edu.co.cda;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Configuración específica para Mockito que evita el uso del agente Java
 */
@TestConfiguration
public class MockitoConfig {

    @PostConstruct
    public void init() {
        // Configuración para desactivar el modo inline de Mockito que requiere el agente Java
        System.setProperty("mockito.mock-maker-inline", "false");
    }

    /**
     * Configura Mockito para usar una implementación que no requiere agente Java
     */
    @Bean
    @Primary
    public String mockitoConfiguration() {
        return "mockitoConfigured";
    }
}
