package is1.order_app.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }   

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        this.authenticateToken(request);
        filterChain.doFilter(request, response);
    }


    private void authenticateToken(@NonNull HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        String authHeader = request.getHeader("Authorization");
        String headerPrefix = "Bearer ";
        if (authHeader == null || !authHeader.startsWith(headerPrefix)) {
            throw new RuntimeException("Invalid or missing authorization header");
        }

        String token = authHeader.substring(headerPrefix.length());
        
        if (!jwtService.validateToken(token)) {
            throw new RuntimeException("Invalid token");
        }

        String username = Jwts.parserBuilder()
                .setSigningKey(jwtService.getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
                
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
