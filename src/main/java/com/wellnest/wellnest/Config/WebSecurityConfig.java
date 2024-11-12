package com.wellnest.wellnest.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests((authorize) ->
                        authorize
                                .requestMatchers("/auth/**").permitAll() // Permite el acceso sin autenticación
                                .anyRequest().authenticated()
                );

        http
                .formLogin(withDefaults());

        http
                .httpBasic(withDefaults());

        return http.build();
    }

}
