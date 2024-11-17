package is1.order_app.security;

import java.util.Date;
import java.util.Optional;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService {
    @Value("${jwt.secret.key}")
    private String jwtSecretKey;

    @Value("${jwt.time.expiration}")
    private long jwtTimeExpiration;


    public String generateToken(JwtUserDetails userDetails) {
        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + jwtTimeExpiration);
    
        return Jwts.builder()
                .subject(userDetails.email())
                .claim("role", userDetails.role())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }
    


    public Optional<JwtUserDetails> extractVerifiedUserDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    
            String email = claims.getSubject();
            String role = claims.get("role", String.class);
    
            return Optional.of(new JwtUserDetails(email, role));
        } catch (ExpiredJwtException e) {
            log.error("Token has expired", e);
        } catch (Exception e) {
            log.error("Token verification failed", e);
        }
        return Optional.empty();
    }
    

    private SecretKey getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
}
