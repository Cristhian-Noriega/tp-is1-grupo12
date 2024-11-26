package is1.order_app.entities;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Nullable
    private String photoUrl;

    @Min(18)
    private Integer age;

    @Pattern(regexp = "Male|Female")
    private String gender;

    private String address;

    @Enumerated(EnumType.STRING) 
    private Role role;

    @Column(nullable = false)
    private Boolean isActive;

    public User(String email, String name, String surname, String password, String photoUrl, Integer age, String gender, String address, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.photoUrl = photoUrl;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.role = role;
        this.isActive = false;
    }
}

