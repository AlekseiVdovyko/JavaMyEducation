package ru.edu.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ru.edu.service.WeatherCache;
import ru.edu.service.WeatherProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:app.properties")
public class Config {

    @Bean
    public Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:db/simple_database.db");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public WeatherProvider weatherProvider() {
        return new WeatherProvider(restTemplate());
    }

    @Bean
    public WeatherCache weatherCache() {
        return new WeatherCache();
    }
}
