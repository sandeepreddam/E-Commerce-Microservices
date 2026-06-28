package com.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityFilterChain(
//            ServerHttpSecurity http) {
//
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeExchange(exchanges -> exchanges
//
//                        .pathMatchers(
//                                "/api/v1/auth/**",
//                                "/api/v1/products/list/**"
//                        ).permitAll()
//
//                        .anyExchange()
//                        .authenticated()
//                )
//                .httpBasic(httpBasic ->
//                        httpBasic.disable())
//                .formLogin(form ->
//                        form.disable())
//                .build();
//    }
//}

@Configuration
public class SecurityConfig {

    public SecurityConfig() {
        System.out.println("CUSTOM SECURITY CONFIG LOADED");
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(
            ServerHttpSecurity http) {

        System.out.println("SECURITY FILTER CHAIN CREATED");

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges ->
                        exchanges.anyExchange().permitAll())
                .build();
    }
}