package is1.order_app.dto;

public record UserRegistrationDTO(
        String name,
        String surname,
        String email,
        String password,
        String gender,
        int age,
        String address,
        String photoUrl
) {
}
