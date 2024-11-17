package is1.order_app.security;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Value("${jwt.time.expiration}")
    private long jwtTimeExpiration;


    public String generateToken(JwtUserDetails userDetails) {
        log.debug("Generating token for email: {}", userDetails.email());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtTimeExpiration);
        return Jwts.builder()
                .setSubject(userDetails.email())
                .claim("role", userDetails.role())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token", e);
        } catch (Exception e) {
            log.error("Invalid JWT token", e);
        }
        return false;
    }

    public Key getSignatureKey() {
        try {
            return Keys.hmacShaKeyFor(jwtSecretKey.getBytes());
        } catch (IllegalArgumentException e) {
            log.error("Invalid JWT secret key: Ensure it's Base64 encoded", e);
            throw e;
        }
    }
}
