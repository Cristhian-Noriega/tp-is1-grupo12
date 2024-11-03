package is1.order_app.dto;

import is1.order_app.entities.User;

public record UserDTO(
        String email,
        String name,
        String surname,
        String photoUrl,
        Integer age,
        String gender,
        String address
) {

    public record ProfileRequestDTO(
            String email,
            String token
    ) {
    }

}
