package udem.edu.co.cda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@Import({TestConfig.class, MockitoConfig.class, NoAgentTestConfig.class})
@TestPropertySource("classpath:application-test.properties")
class CdaApplicationTests {

	static {
		// Configuración para usar el mock-maker-inline de Mockito
		System.setProperty("mockito.mock-maker-inline", "true");
	}

	@Test
	void contextLoads() {
		// Esta prueba verifica que el contexto de Spring se carga correctamente
	}

}
