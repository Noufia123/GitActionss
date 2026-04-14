package com.wpoms.admin.utilities.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter; // Inject your filter

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Swagger endpoints
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html")
                        .permitAll()

                        // Public APIs
                        .requestMatchers(
                                "/api/login",
                                "/api/vendor/register",
                                "/api/customer/register-customer",
                                "/api/admin/register-manufacturer")
                        .permitAll()

                        .requestMatchers(
                                "/api/customer/**")
                        .hasAuthority("CUSTOMER")
                        .requestMatchers(
                                "/api/vendor/**")
                        .hasAuthority("VENDOR")
                        .requestMatchers(
                                "/api/admin/**")
                        .hasAuthority("MANUFACTURER")

                        // Secure all other APIs
                        .anyRequest().authenticated())

                // Add JWT filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}