package udem.edu.co.cda.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource dataSource() {
        String driver = env.getProperty("spring.datasource.driver-class-name");
        String url = env.getProperty("spring.datasource.url");
        String user = env.getProperty("spring.datasource.username");
        String pass = env.getProperty("spring.datasource.password");

        // Loguea para debug
        System.out.println("[JpaConfig] driverClassName: " + driver);
        System.out.println("[JpaConfig] url: " + url);
        System.out.println("[JpaConfig] username: " + user);

        if (driver == null || driver.isEmpty()) {
            throw new IllegalArgumentException("La propiedad 'spring.datasource.driver-class-name' está vacía o no configurada");
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("La propiedad 'spring.datasource.url' está vacía o no configurada");
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pass);
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("udem.edu.co.cda.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        setPropertySafe(properties, "hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));  // corrige a la propiedad correcta
        setPropertySafe(properties, "hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        setPropertySafe(properties, "hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        setPropertySafe(properties, "hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        em.setJpaProperties(properties);

        return em;
    }

    private void setPropertySafe(Properties props, String key, String value) {
        if (value != null) {
            props.setProperty(key, value);
        } else {
            System.err.println("[JpaConfig] Propiedad no encontrada o null: " + key);
        }
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
