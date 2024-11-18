package is1.order_app.dto;

public record UserDTO(
        String email,
        String name,
        String surname,
        String photoUrl,
        Integer age,
        String gender,
        String address,
        String role
) {
}
