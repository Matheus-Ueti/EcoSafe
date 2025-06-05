package com.example.EcoSafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Change from "/" to "/**" to match all paths
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")  // Added OPTIONS
            .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", 
                          "Access-Control-Request-Method", "Access-Control-Request-Headers")
            .exposedHeaders("Authorization")
            .maxAge(3600);
    }
}
