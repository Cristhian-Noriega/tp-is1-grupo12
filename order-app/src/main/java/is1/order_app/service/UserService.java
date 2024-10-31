package is1.order_app.service;

import is1.order_app.dto.LoginDTO;
import is1.order_app.exceptions.DuplicatedUserEmailException;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.User;
import is1.order_app.exceptions.UserNotFoundException;
import is1.order_app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




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

    public UserDTO getUserByEmail(String email) {
        Optional<UserDTO> userDTO = userRepository.findByEmail(email).map(UserDTO::fromUser);
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + email);
        }
        return userDTO.get();
    }


    public String loginUserToken(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.email());
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginDTO.password())) {
            User user = userOpt.get();
            String token = generateToken(user.getEmail());
            user.setAuthToken(token);
            userRepository.save(user);
            return token;
        }
        return null;
    }


    public boolean loginUser(LoginDTO loginDTO) {
        Optional<User> user =  userRepository.findByEmail(loginDTO.email());
        return user.isPresent() && user.get().getPassword().equals(loginDTO.password());
    }

    private String generateToken(String email) {
        try {
            String input = email + System.currentTimeMillis();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating token", e);
        }
    }

    public boolean validateToken(String email, String token) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent() && token.equals(userOpt.get().getAuthToken());
    }


}



