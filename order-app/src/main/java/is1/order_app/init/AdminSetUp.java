package is1.order_app.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import is1.order_app.entities.User;
import is1.order_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import is1.order_app.entities.Role;

import org.springframework.beans.factory.annotation.Value;

@Slf4j
@RequiredArgsConstructor
@Component
public class AdminSetUp implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public void run(String... args) {
        initAdmin();
    }


    private void initAdmin() {
        Optional<User> existingAdmin = userRepository.findById(adminEmail);

        if (existingAdmin.isPresent()) {
            log.info("Admin user already exists with email: {}", adminEmail);
            return;
        }

        try {
            User user = new User(
                adminEmail,
                "Admin",
                "System",
                passwordEncoder.encode(adminPassword),
                null,
                100,
                "Male",
                "System address",
                Role.ADMIN
            );

            userRepository.save(user);
            log.info("Admin user created with email: {}", adminEmail);
        } catch (Exception e) {
            log.error("Error creating admin user", e);
            throw e;
        }
    }
}
