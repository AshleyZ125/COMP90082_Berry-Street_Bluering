package berryStreet.bluering.backend.controller;


import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import berryStreet.bluering.backend.service.RegisterService;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;

    @PostMapping("/superLogin")
    public AjaxResult superLogin(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User user = new User();
            user.setEmail(map.get("email") + "");
            user.setPassword(map.get("email") + "");
            user.setRole("supervisor");
            User result = loginService.login(user);
            if (result.getUid() != null) {
                return AjaxResult.success(result);
            } else {
                return AjaxResult.warn("The user is not exist!");
            }
        } else {
            return AjaxResult.error("Input Empty!");
        }
    }

    @PostMapping("/register")
    public int register(User user) {
        int result = registerService.register(user);
        return result;
    }
}


