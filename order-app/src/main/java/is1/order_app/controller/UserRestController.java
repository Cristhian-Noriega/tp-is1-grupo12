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
import is1.order_app.exceptions.WrongPasswordException;
import is1.order_app.exceptions.UserNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;
    //private final JwtUtil jwtUtil;

    public UserRestController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
       // this.jwtUtil = jwtUtil;
    }
@PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserRegistrationDTO registration) {
        UserDTO user = userService.registerUser(registration);
        return ResponseEntity.ok(user);
}


@PostMapping("/login")
public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
    String token = userService.loginUserToken(loginDTO);
    if (token != null) {
        return ResponseEntity.ok(token);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}

@PostMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(@Valid @RequestBody UserDTO.ProfileRequestDTO request) {
        String email = request.email();
        String token = request.token();

        if (userService.validateToken(email, token)) {
            UserDTO user = userService.getUserByEmail(email);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
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
