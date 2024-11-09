package is1.order_app.service;

import is1.order_app.controller.UserRestController;
import is1.order_app.dto.UserRegistrationDTO;
import is1.order_app.dto.LoginDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {UserService.class, UserRestController.class})
@EnableAutoConfiguration
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestController userRestController;

    @Test
    public void testRegisterAndLogin() {
        String email = "mm@gmail.com";
        String password = "admin";
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("Martin", "Martinez", email, password, "male", 31, "Av. Paseo Colon 850", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/1200px-Default_pfp.svg.png");
        userRestController.registerUser(registrationDTO);
        LoginDTO loginDTO = new LoginDTO(email, password);
        assertNotNull(userRestController.loginUser(loginDTO)); // loging in must return a token
    }
}
