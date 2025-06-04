package udem.edu.co.cda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import udem.edu.co.cda.config.TestContainerConfig;

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = TestContainerConfig.class)
class CdaApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring se cargue correctamente con Testcontainers
    }
}
