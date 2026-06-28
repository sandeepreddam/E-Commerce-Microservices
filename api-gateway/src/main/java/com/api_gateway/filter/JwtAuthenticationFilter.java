package com.api_gateway.filter;

import com.api_gateway.security.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class JwtAuthenticationFilter
        implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        // Public APIs
        if (path.startsWith("/api/v1/auth")
                || path.startsWith("/api/v1/products/list")) {

            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization");

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        try {
            String token = authHeader.substring(7);

            JwtUtil.validateToken(token);

            System.out.println("JWT VALID");

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            "authenticated-user",
                            null,
                            Collections.emptyList()
                    );
            System.out.println("Authentication created");
            return chain.filter(exchange)
                    .contextWrite(
                            ReactiveSecurityContextHolder
                                    .withAuthentication(authentication)
                    );
        } catch (Exception e) {

            System.out.println("JWT INVALID");
            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
