package udem.edu.co.cda;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Configuración para ejecutar pruebas sin necesidad de agentes Java
 */
@TestConfiguration
public class NoAgentTestConfig {

    static {
        // Estas propiedades se establecen al inicio de la ejecución
        System.setProperty("mockito.mock-maker-inline", "false");
        System.setProperty("spring.test.mockito.inline", "false");
    }

    /**
     * Bean para configurar el entorno de pruebas
     */
    @Bean
    public String testConfigBean() {
        return "testConfigWithoutAgent";
    }
}
