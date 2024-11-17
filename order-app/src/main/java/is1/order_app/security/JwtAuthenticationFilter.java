package is1.order_app.security;

import java.io.IOException;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        authenticateToken(request);
        filterChain.doFilter(request, response);
    }

    private void authenticateToken(@NonNull HttpServletRequest request) {
   
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        String authHeader = request.getHeader("Authorization");
        log.debug("Auth header: {}", authHeader); 

        String headerPrefix = "Bearer ";
        if (authHeader == null || !authHeader.startsWith(headerPrefix)) {
            return;
        }

        String token = authHeader.substring(headerPrefix.length());
        log.debug("Extracted token: {}", token); 

        jwtService.extractVerifiedUserDetails(token).ifPresentOrElse(userDetails -> {
            var authToken = new UsernamePasswordAuthenticationToken(
                userDetails, 
                null, 
                List.of(new SimpleGrantedAuthority("ROLE_" + userDetails.role()))
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            
        }, () -> {
            log.error("Invalid token");
        });
    }
}