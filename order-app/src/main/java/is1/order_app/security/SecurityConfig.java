package is1.order_app.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                // Add session management

                .authorizeHttpRequests(auth -> auth
                        // Authentication endpoints
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/users/requestPassChange").permitAll()
                        .requestMatchers("/users/passChange").permitAll()

                        // User endpoints
                        .requestMatchers("/users/allProfiles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                        .requestMatchers("/users/privateProfile").hasRole("USER")
                        .requestMatchers("/users/requestPassChange").permitAll()
                        .requestMatchers("/users/**").authenticated()

                        // Product endpoints
                        .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/{productId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/{productId}/stock").hasRole("ADMIN")
                        .requestMatchers("/products/**").authenticated()

                        // Order endpoints
                        .requestMatchers(HttpMethod.POST, "/orders/create").hasRole("USER")
                        .requestMatchers("/orders/{orderId}/cancelByUser").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/orders/{orderId}/availableCommands").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/orders/{orderId}/executeCommand").hasRole("ADMIN")
                        .requestMatchers("/orders/**").hasRole("USER")
                        .requestMatchers("/orders/**").authenticated()
                        .anyRequest().denyAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
