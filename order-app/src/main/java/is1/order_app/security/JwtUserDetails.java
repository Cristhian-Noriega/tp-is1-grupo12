package is1.order_app.security;


public record JwtUserDetails (
        String email,
        String role
) {}