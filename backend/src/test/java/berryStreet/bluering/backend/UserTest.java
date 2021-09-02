package berryStreet.bluering.backend;

import berryStreet.bluering.backend.controller.UserController;
import berryStreet.bluering.backend.entity.RegisterUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired
    UserController userController;
    @Test
    public void UserRegisterTest(){
        RegisterUser registerUser = RegisterUser.builder().username("YUJGUAN").email("yujguan@student.unimelb.edu.au")
                .password("123456").role("supervisor").build();
        userController.register(registerUser);
        RegisterUser supervisor = RegisterUser.builder().username("guan").email("4444@qq.com")
                .password("223").role("supervisor").inviteCode("123123123")
                .build();
        userController.register(supervisor);
    }
}
