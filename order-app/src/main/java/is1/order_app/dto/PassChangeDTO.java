package is1.order_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PassChangeDTO(
        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "New Password is required")
        String newPassword
) {
}
