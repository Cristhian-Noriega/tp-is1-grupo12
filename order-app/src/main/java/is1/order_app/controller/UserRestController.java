package is1.order_app.controller;

import is1.order_app.dto.*;
import is1.order_app.service.UserService;
import is1.order_app.exceptions.WrongPasswordException;
import is1.order_app.exceptions.UserNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

@PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        UserDTO user = userService.registerUser(registrationDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            String token = userService.loginUser(loginDTO);
            return ResponseEntity.ok(token);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed because user was not found.");
        } catch (WrongPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed because of wrong password.");
        }
    }

    @PostMapping("/requestPassChange")
    public ResponseEntity<String> requestPasswordChange(@RequestParam String email) {
        userService.askMailRestorePassword(email);
        return ResponseEntity.ok("Password recovery mail sent");
    }

@PatchMapping("/passChange")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PassChangeDTO passChangeDTO) {
        boolean response = userService.changePassword(passChangeDTO);
        if (response) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
        }
    }

@GetMapping("/privateProfile")
    public ResponseEntity<UserDTO> getProfile(@Valid @RequestBody ProfileRequestDTO requestDTO) {
        String email = requestDTO.email();
        String token = requestDTO.token();

        if (userService.validateToken(email, token)) {
            UserDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

@GetMapping("/publicProfile")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

@GetMapping("/allProfiles")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
