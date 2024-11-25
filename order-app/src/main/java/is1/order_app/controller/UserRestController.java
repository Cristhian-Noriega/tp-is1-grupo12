package is1.order_app.controller;

import is1.order_app.dto.*;
import is1.order_app.entities.User;
import is1.order_app.exceptions.UserNotFoundException;
import is1.order_app.security.JwtUserDetails;
import is1.order_app.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal User authenticatedUser) {
        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return ResponseEntity.ok(userService.getUserByEmail(authenticatedUser.getEmail()));
    }

    @GetMapping("/publicProfile")
    public ResponseEntity<UserDTO> getUserByEmail(@AuthenticationPrincipal JwtUserDetails authenticatedUser) {
        try {
            return ResponseEntity.ok(userService.getUserByEmail(authenticatedUser.email()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/allProfiles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
