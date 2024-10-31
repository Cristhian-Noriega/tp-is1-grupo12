package is1.order_app.controller;
import is1.order_app.dto.UserDTO;
import is1.order_app.dto.LoginDTO;
import is1.order_app.dto.PassChangeDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
@PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registration) {
        UserDTO user = userService.registerUser(registration);
        return ResponseEntity.ok(user);
}

@PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        boolean response = userService.loginUser(loginDTO);
        if (response) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

@PostMapping("/passChange")
    public ResponseEntity<String> changePassword(@Valid @RequestBody PassChangeDTO passChangeDTO) {
        boolean response = userService.changePassword(passChangeDTO);
        if (response) {
            return ResponseEntity.ok("Password changed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
        }
    }
@GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // METODO momentaneo para testear la api
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
@GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
