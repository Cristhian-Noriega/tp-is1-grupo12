package is1.order_app.service;

import is1.order_app.dto.LoginDTO;
import is1.order_app.exceptions.DuplicatedUserEmailException;
import is1.order_app.exceptions.WrongPasswordException;
import is1.order_app.exceptions.UserNotFoundException;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.PassChangeDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.User;
import is1.order_app.mapper.UserMapper;
import is1.order_app.repository.UserRepository;
import is1.order_app.service.mails_sevice.EmailSenderService;

import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, EmailSenderService emailSenderService, UserMapper userMapper) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO registerUser(UserRegistrationDTO registration) {
        userRepository.findByEmail(registration.email()).ifPresent(user -> {
            throw new DuplicatedUserEmailException("The email is already taken");
        });
        User user = userMapper.toEntity(registration);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserByEmail(String email) {
        Optional<UserDTO> userDTO = userRepository.findByEmail(email).map(userMapper::toDTO);
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + email);
        }
        return userDTO.get();
    }

    private boolean confirmPassword(User user, String possiblePassword) {
        return user.getPassword().equals(possiblePassword);
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.email());
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + loginDTO.email());
        }
        if (this.confirmPassword(userOpt.get(), loginDTO.password())) {
            User user = userOpt.get();
            String token = generateToken(user.getEmail());
            user.setAuthToken(token);
            userRepository.save(user);
            return token;
        } else {
            throw new WrongPasswordException("Login to " + loginDTO.email() + " failed because of wrong password.");
        }
    }

    public void askMailRestorePassword(String email) {
        userRepository.findByEmail(email).ifPresentOrElse(user -> emailSenderService.sendPassworChangedMail(email), () -> {
            throw new UserNotFoundException("User not found with email " + email);
        });
    }

    public boolean changePassword(PassChangeDTO passChangeDTO) {
        Optional<User> user = userRepository.findByEmail(passChangeDTO.email());
        if (user.isEmpty()) {
            return false;
        }
        user.get().setPassword(passChangeDTO.newPassword());
        return true;
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



