package is1.order_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDTO(
                @NotBlank(message = "Name is required") String name,
                @NotBlank(message = "Surname is required") String surname,
                @Email(message = "Email is not valid") @NotBlank(message = "Email is required") String email,
                @NotBlank(message = "Password is required") String password,
                @NotBlank(message = "Gender is required") String gender,
                @NotNull(message = "Age is required") @Min(value = 18, message = "You must be at least 18 years old") Integer age,
                String address,
                String photoUrl) {
}
