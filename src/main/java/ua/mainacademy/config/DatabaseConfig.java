package ua.mainacademy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Slf4j
public class DatabaseConfig {

    @Bean
    @Profile("prod")
    DataSource getPostgresDataSource() {
        log.info("Try to connect to spring-eshop-g48");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("248842");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/spring-eshop-g48");
        return new HikariDataSource(config);
    }

    @Bean
    @Profile("test")
    DataSource getTestPostgresDataSource() {
        log.info("Try to connect to test-spring-eshop-g48");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("248842");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/test-spring-eshop-g48");
        return new HikariDataSource(config);
    }

}
