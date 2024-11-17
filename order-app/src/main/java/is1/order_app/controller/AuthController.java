package is1.order_app.controller;

import java.net.Authenticator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

import is1.order_app.dto.LoginDTO;
import is1.order_app.dto.LoginResponseDTO;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.exceptions.UserNotFoundException;
import is1.order_app.exceptions.WrongPasswordException;
import is1.order_app.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticatorManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticatorManager;
    }

    @PostMapping("/register")  
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        UserDTO user = userService.registerUser(registrationDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            LoginResponseDTO response = userService.loginUser(loginDTO);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | WrongPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
