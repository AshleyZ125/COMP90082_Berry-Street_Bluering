package berryStreet.bluering.backend.controller;

import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class UserController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/register")
    public int register(User user){
        int result = registerService.register(user);
        return result;
    }

}
