package is1.order_app.dto;

import is1.order_app.entities.User;

public record UserDTO(
        String email,
        String name,
        String surname,
        String photoUrl,
        int age,
        String gender,
        String address
) {
    public static UserDTO fromUser(User user) {
        return new UserDTO(
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getPhotoUrl(),
                user.getAge(),
                user.getGender(),
                user.getAddress()
        );
    }
}
