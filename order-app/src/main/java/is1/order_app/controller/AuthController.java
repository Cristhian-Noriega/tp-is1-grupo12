package is1.order_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.micrometer.common.lang.NonNull;
import is1.order_app.dto.LoginDTO;
import is1.order_app.dto.LoginResponseDTO;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")  
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        UserDTO user = userService.registerUser(registrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@NonNull @RequestBody  LoginDTO loginDTO) {
        LoginResponseDTO response = userService.login(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-email/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable @NonNull String email) {
        userService.verifyEmail(email);
        return ResponseEntity.ok("Email verified");
    }
}
