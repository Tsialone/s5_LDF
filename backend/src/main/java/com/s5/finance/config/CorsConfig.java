package com.s5.finance.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")    // toutes les routes
                        .allowedOrigins("*")  // toutes les origines
                        .allowedMethods("*")  // toutes les méthodes
                        .allowedHeaders("*"); // tous les headers
            }
        };
    }
}
