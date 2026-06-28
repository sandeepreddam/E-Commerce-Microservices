package com.auth_service.filter;

import com.auth_service.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private JWTService jwtService;

    public JWTFilter(
            JWTService jwtService,
            UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token!=null && token.startsWith("Bearer ")){
            String rawToken = token.substring(7);

            String username =
                    jwtService.validateTokenAndRetrieveSubject(rawToken);

            if (username !=null && SecurityContextHolder
                    .getContext().getAuthentication()== null
            ){

                var userDetails =
                        userDetailsService.loadUserByUsername(username);

                var authToken =
                        new UsernamePasswordAuthenticationToken(
                        userDetails,null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().
                                buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }
        filterChain.doFilter(request,response);
    }
}
