package is1.order_app.service;

import is1.order_app.Exceptions.DuplicatedUserEmailException;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.User;
import is1.order_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO registerUser(UserRegistrationDTO registration) {
        userRepository.findByEmail(registration.email()).ifPresent(user -> {
            throw new DuplicatedUserEmailException("The email is already taken");
        });

        User user = new User(
                registration.email(),
                registration.name(),
                registration.surname(),
                registration.password(),
                registration.photoUrl(),
                registration.age(),
                registration.gender(),
                registration.address()
        );
        User savedUser = userRepository.save(user);
        return UserDTO.fromUser(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::fromUser)
                .collect(Collectors.toList());
    }
}
