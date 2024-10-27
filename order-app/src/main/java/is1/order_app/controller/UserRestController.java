package is1.order_app.controller;

import is1.order_app.dto.UserDTO;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.entities.User;
import is1.order_app.service.UserService;
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
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegistrationDTO registration) {
        UserDTO user = userService.registerUser(registration);
        return ResponseEntity.ok(user);
    }

@GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // METODO momentaneo para testear la api
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
@GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
