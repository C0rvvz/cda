package udem.edu.co.cda;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

@TestConfiguration
public class TestcontainersConfiguration {

    // Definimos el contenedor MySQL
    private static final MySQLContainer<?> mysqlContainer;

    static {
        mysqlContainer = new MySQLContainer<>("mysql:8.0")
                .withDatabaseName("testdb")
                .withUsername("testuser")
                .withPassword("testpass");
        mysqlContainer.start();
    }

    @Bean
    @Primary // Prioriza esta configuración de datasource en tests
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(mysqlContainer.getDriverClassName());
        dataSource.setUrl(mysqlContainer.getJdbcUrl());
        dataSource.setUsername(mysqlContainer.getUsername());
        dataSource.setPassword(mysqlContainer.getPassword());
        return dataSource;
    }
}
