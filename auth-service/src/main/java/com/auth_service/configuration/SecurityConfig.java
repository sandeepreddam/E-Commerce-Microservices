package com.auth_service.configuration;

import com.auth_service.filter.JWTFilter;
import com.auth_service.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    private CustomerUserDetailsService customerUserDetailsService;
    private JWTFilter filter;

    public SecurityConfig(
            CustomerUserDetailsService customerUserDetailsService,
            JWTFilter filter
    ){
        this.customerUserDetailsService = customerUserDetailsService;
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                req->{
                    req.requestMatchers(
                            "/api/v1/auth/**"
                            ).permitAll()

                            .requestMatchers("/api/v1/message/customer")
                            .hasRole("CUSTOMER")

                            .requestMatchers("/api/v1/message/store")
                            .hasRole("STORE")

                            .requestMatchers("/api/v1/message/admin")
                            .hasRole("ADMIN")
                    .anyRequest().authenticated();
                })
        .addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public AuthenticationProvider authenticationProvider(
            CustomerUserDetailsService customerUserDetailsService,
            PasswordEncoder passwordEncoder
    ){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        customerUserDetailsService
                );
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception{
        return config.getAuthenticationManager();
    }
}

