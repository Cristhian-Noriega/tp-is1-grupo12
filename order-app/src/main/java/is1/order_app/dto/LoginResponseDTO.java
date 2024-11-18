package is1.order_app.dto;

public record LoginResponseDTO(
        String id,
        String name,
        String role,
        String token
) {
}
