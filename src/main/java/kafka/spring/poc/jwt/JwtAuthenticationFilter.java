package kafka.spring.poc.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * The filter intercepts HTTP requests to validate JWT tokens and ensure that requests will include a valid
 * authorization header with a Bearer Token. If a valid Token is found the user is authenticated.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    /**
     * Constructs a new {@code JwtAuthenticationFilter} with the specified {@link JwtUtil}.
     *
     * @param jwtUtil Utility class for JWT operations such as generate token and data extraction.
     */
    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Filters incoming requests by checking for a valid JWT token in the "Authorization" header.
     * If the token is valid, the user's authentication is set in the {@link SecurityContextHolder}.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @param chain    The filter chain to pass the request forward.
     * @throws ServletException If an error occurs during filtering.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        // If the condition is not fulfilled, pass the request to the next filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // extract only the Token from the whole header
        String token = authHeader.substring(7);
        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractUsername(token);
            // create a Spring Security Auth Obj with no credential, since we use JWT
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Ensure next filter gets executed
        chain.doFilter(request, response);
    }
}