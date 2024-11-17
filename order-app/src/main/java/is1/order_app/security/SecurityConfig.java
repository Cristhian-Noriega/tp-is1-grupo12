package is1.order_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("users/allProfiles").hasRole("ADMIN")
                .requestMatchers("/users/privateProfile").hasRole("USER")
                .requestMatchers("/users/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                .requestMatchers("/products/**").authenticated()
                .requestMatchers("/orders/**").authenticated()
                .requestMatchers(HttpMethod.POST,"/orders/create").hasRole("USER")
                .requestMatchers("/orders/{orderId}/cancelByUser").hasRole("USER")
                .requestMatchers("/orders/user/**").hasRole("USER")
            .anyRequest().authenticated());
            return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
