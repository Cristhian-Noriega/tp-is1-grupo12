package is1.order_app.service;

import is1.order_app.dto.LoginDTO;
import is1.order_app.exceptions.DuplicatedUserEmailException;
import is1.order_app.exceptions.WrongPasswordException;
import is1.order_app.exceptions.UserNotFoundException;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.PassChangeDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.User;
import is1.order_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    public UserService(UserRepository userRepository, EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
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

    private boolean confirmPassword(Optional<User> user, String possiblePassword) {
        return user.get().getPassword().equals(possiblePassword);
    }

    public boolean loginUser(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.email());
        if (!(user.isPresent())) {
            throw new UserNotFoundException("User not found with email " + loginDTO.email());
        }
        if (confirmPassword(user, loginDTO.password())) {
            return true;
        } else {
            throw new WrongPasswordException("Login to " + loginDTO.email() + " failed because of wrong password.");
        }
    }

    public void askMailRestorePassword(String email) {
        userRepository.findByEmail(email).ifPresentOrElse(user -> {
            emailSenderService.restorePasswordMail(email);
        }, () -> {
            throw new UserNotFoundException("User not found with email " + email);
        });
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
