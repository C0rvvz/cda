package udem.edu.co.cda;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@TestConfiguration
@EnableAutoConfiguration
@EntityScan(basePackages = "udem.edu.co.cda.entities")
@EnableJpaRepositories(basePackages = "udem.edu.co.cda.repository")
@EnableTransactionManagement
public class TestConfig {
    // Esta clase proporciona configuración específica para pruebas

    /**
     * Este método proporciona un bean de ejemplo para pruebas
     * Se puede utilizar para configurar mocks o beans específicos para las pruebas
     */
    @Bean
    public String testBean() {
        return "testValue";
    }
}