package is1.order_app.mapper;

import is1.order_app.dto.UserDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.Role;
import is1.order_app.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getEmail(),
                user.getName(),
                user.getSurname(),
                user.getPhotoUrl(),
                user.getAge(),
                user.getGender(),
                user.getAddress(),
                user.getRole().name()
        );  
    }

    public User toEntity(UserRegistrationDTO registrationDTO) {
        return new User(
                registrationDTO.email(),
                registrationDTO.name(),
                registrationDTO.surname(),
                registrationDTO.password(),
                registrationDTO.photoUrl(),
                registrationDTO.age(),
                registrationDTO.gender(),
                registrationDTO.address(),
                Role.USER
        );
    }
}
