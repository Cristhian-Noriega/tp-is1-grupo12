package is1.order_app.service;

import is1.order_app.dto.LoginDTO;
import is1.order_app.dto.LoginResponseDTO;
import is1.order_app.exceptions.DuplicatedUserEmailException;
import is1.order_app.exceptions.WrongPasswordException;
import is1.order_app.exceptions.UserNotFoundException;
import is1.order_app.exceptions.UserNotVerified;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.PassChangeDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.User;
import is1.order_app.mapper.UserMapper;
import is1.order_app.repository.UserRepository;
import is1.order_app.security.JwtService;
import is1.order_app.security.JwtUserDetails;
import is1.order_app.service.mails_sevice.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserDTO registerUser(UserRegistrationDTO registration) {
        userRepository.findByEmail(registration.email()).ifPresent(user -> {
            throw new DuplicatedUserEmailException("The email is already taken");
        });
        User user = userMapper.toEntity(registration);
        //encode the user password to pass it to store it in the db
        user.setPassword(passwordEncoder.encode(registration.password()));
        userRepository.save(user);
        emailSenderService.sendMailToVerifyAccount(registration.email());

        return userMapper.toDTO(user);
    }

    public LoginResponseDTO login(LoginDTO loginDTO) {
        Optional<User> userOpt = userRepository.findByEmail(loginDTO.email());
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + loginDTO.email());
        }
        User user = userOpt.get();

        if (!user.getIsActive()) {
            throw new UserNotVerified("User is not verified " + loginDTO.email());
        }

        if (!passwordEncoder.matches(loginDTO.password(), user.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }

        JwtUserDetails userDetails = new JwtUserDetails(user.getEmail(), user.getRole().name());

        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(user.getEmail(), user.getName(), user.getRole().toString(), token);
    }


    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println(user);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + email);
        }

        UserDTO userDTO = userMapper.toDTO(user.get());

        return userDTO;
    }


    public void askMailRestorePassword(String email) {
        userRepository.findByEmail(email).ifPresentOrElse(user -> emailSenderService.sendPassworChangedMail(email), () -> {
            throw new UserNotFoundException("User not found with email " + email);
        });
    }

    public void verifyEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found with email " + email);
        }
        User user = userOpt.get();
        user.setIsActive(true);
        userRepository.save(user);
    }



    public boolean changePassword(PassChangeDTO passChangeDTO) {
        Optional<User> userOpt = userRepository.findByEmail(passChangeDTO.email());
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        user.setPassword(passwordEncoder.encode(passChangeDTO.newPassword()));
        userRepository.save(user);
        return true;
    }

    public void deleteUser(String email) {
        if (!userRepository.existsById(email)) {
            throw new UserNotFoundException("User not found with id " + email);
        }
        userRepository.deleteById(email);
    }

}



