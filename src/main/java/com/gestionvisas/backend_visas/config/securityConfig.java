package com.gestionvisas.backend_visas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {

/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Definimos secret y expiración del token
    public static final String JWT_SECRET = "mi_clave_super_segura_12345";
    public static final long JWT_EXPIRATION_MS = 86400000; // 1 día*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // deshabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // permite todas las requests
                );

        return http.build();
    }

}
