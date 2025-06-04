package udem.edu.co.cda;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class MockitoConfig {

    static {
        System.setProperty("mockito.mock-maker-inline", "true");
    }

    @PostConstruct
    public void init() {
        System.setProperty("mockito.mock-maker-inline", "true");
    }
}
