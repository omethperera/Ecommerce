package com.ecommerce.apigateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtParser jwtParser;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtParser jwtParser, ObjectMapper objectMapper) {
        this.jwtParser = jwtParser;
        this.objectMapper = objectMapper;
    }

    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/auth/",
            "/eureka"
    );

    private static final List<String> PUBLIC_GET_PATHS = List.of(
            "/api/products",
            "/api/categories"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }
        
        boolean alwaysPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        boolean getPublic = "GET".equalsIgnoreCase(method)
                && PUBLIC_GET_PATHS.stream().anyMatch(path::startsWith);

        return alwaysPublic || getPublic;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendError(response, HttpStatus.UNAUTHORIZED, "Missing or malformed Authorization header");
            return;
        }

        String token = authHeader.substring(7);

        Claims claims;
        try {
            claims = jwtParser
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            log.warn("JWT validation failed: {}", e.getMessage(), e);
            sendError(response, HttpStatus.UNAUTHORIZED, "Invalid or expired token");
            return;
        }

        String username = claims.getSubject();
        String role = claims.get("role", String.class);

        if (username == null || username.isBlank()) {
            sendError(response, HttpStatus.UNAUTHORIZED, "Token subject is missing");
            return;
        }

        MutableHttpServletRequest wrapper = new MutableHttpServletRequest(request);
        wrapper.putHeader("X-User-Username", username);
        wrapper.putHeader("X-User-Role", role != null ? role : "");
        wrapper.removeHeader("Authorization");

        log.debug("JWT valid — user: {}, role: {}, path: {}", username, role, request.getRequestURI());

        filterChain.doFilter(wrapper, response);
    }

    private void sendError(HttpServletResponse response,
                           HttpStatus status,
                           String message) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Map.of(
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message
        ));
    }
}