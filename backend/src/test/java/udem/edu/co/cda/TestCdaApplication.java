package udem.edu.co.cda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Usa el perfil "test" para configuración específica si tienes
public class TestCdaApplication {

    @Test
    void contextLoads() {
        // Este test verifica que el contexto de Spring Boot se carga correctamente
    }
}
