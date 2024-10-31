package is1.order_app.service;

import is1.order_app.dto.LoginDTO;
import is1.order_app.exceptions.DuplicatedUserEmailException;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.PassChangeDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.User;
import is1.order_app.exceptions.UserNotFoundException;
import is1.order_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final is1.order_app.service.EmailSenderService emailSenderService;

    public UserService(UserRepository userRepository) {
        this.emailSenderService = new is1.order_app.service.EmailSenderService();
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

    public UserDTO getUserByEmail(String email) {
        Optional<UserDTO> userDTO = userRepository.findByEmail(email).map(UserDTO::fromUser);
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + email);
        }
        return userDTO.get();
    }

    public boolean loginUser(LoginDTO loginDTO) {
        Optional<User> user =  userRepository.findByEmail(loginDTO.email());
        return user.isPresent() && user.get().getPassword().equals(loginDTO.password());
    }

    public void askMailRestorePassword(String email) {
        userRepository.findByEmail(email).ifPresentOrElse(user -> {
            emailSenderService.restorePasswordMail(email);
        }, () -> {
            throw new UserNotFoundException("User not found with email " + email);
        });
    }

    public void restorePassword(PassChangeDTO passChangeDTO) {
        UserDTO userDTO = getUserByEmail(passChangeDTO.email());
        User user = new User(
                userDTO.email(),
                userDTO.name(),
                userDTO.surname(),
                passChangeDTO.newPassword(),
                userDTO.photoUrl(),
                userDTO.age(),
                userDTO.gender(),
                userDTO.address()
        );
        userRepository.save(user);
    }
    
    public boolean changePassword(PassChangeDTO passChangeDTO) {
        Optional<User> user = userRepository.findByEmail(passChangeDTO.email());
        if (!(user.isPresent())) {
            return false;
        }
        user.get().setPassword(passChangeDTO.newPassword());
        return true;
    }
}
