package berryStreet.bluering.backend.controller;


import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import berryStreet.bluering.backend.service.RegisterService;
import berryStreet.bluering.backend.service.ResetService;
import berryStreet.bluering.backend.service.DeleteService;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ResetService resetService;
    @Autowired
    private DeleteService deleteService;

    @PostMapping("/user/superLogin")
    public AjaxResult superLogin(@RequestBody Map<String, Object> map) {
        return login(map,"supervisor");
    }

    @PostMapping("/user/lecLogin")
    public AjaxResult lecLogin(@RequestBody Map<String, Object> map) {
        return login(map,"LEC");
    }

    private AjaxResult login(Map<String, Object> map, String role){
        System.out.println("login start");
        if (map != null) {
            User user = new User();
            user.setEmail(map.get("email") + "");
            user.setPassword(map.get("password") + "");
            user.setRole(role);
            User result = loginService.checkUserExist(user);
            if (result.getUsername() != null) {System.out.println(result);
                return AjaxResult.success(result);
            } else {
                System.out.println("warn");
                return AjaxResult.warn("The user does not exist!");
            }
        } else {
            System.out.println("error");
            return AjaxResult.error("Input Empty!");
        }
    }

    @PostMapping("/user/register")
    public int register(User user) {
        int result = registerService.register(user);
        return result;
    }

    @PostMapping("/user/superResetPassword")
    public AjaxResult superResetPassword(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User userQueried = resetService.queryUserByEmail(map.get("email") + "");
            if(userQueried!=null){
                if(userQueried.getPassword().equals(map.get("oldPassword") + "")){
                    int result = resetService.resetPassword(userQueried.getUID(), map.get("newPassword") + "");
                    if(result==0){
                        return AjaxResult.warn("Fail in password reset!");
                    }else {
                        return AjaxResult.success("Reset password successfully");
                    }
                }else{
                    return AjaxResult.warn("Password is wrong!");
                }
            }else{
                return AjaxResult.warn("The user does not exist!");
            }
        } else {
            return AjaxResult.error("Input Empty!");
        }
    }
    @PostMapping("/user/superResetEmail")
    public AjaxResult superResetEmail(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User userQueried = resetService.queryUserByUID((int)map.get("UID"));
            if(userQueried!=null){
                if(resetService.queryUserByEmail(map.get("newEmail")+"")!=null){
                    return AjaxResult.warn("New email has been bound!");
                }else{
                    int result = resetService.resetEmail(userQueried.getUID(), map.get("newEmail")+"");
                    if(result==0){
                        return AjaxResult.warn("Fail in email reset!");
                    }else {
                        return AjaxResult.success("Reset email successfully");
                    }
                }
            }else{
                return AjaxResult.warn("The user does not exist!");
            }
        } else {
            return AjaxResult.error("Input Empty!");
        }
    }
    @PostMapping("/user/superDelete")
    public AjaxResult superDelete(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User userQueried = resetService.queryUserByUID((int)map.get("UID"));
            if(userQueried!=null){
                int result = deleteService.deleteUser(userQueried.getUID());
                if(result==0){
                    return AjaxResult.warn("Fail in user deletion!");
                }else {
                    return AjaxResult.success("Delete user successfully");
                }
            }else{
                return AjaxResult.warn("The user does not exist!");
            }
        } else {
            return AjaxResult.error("Input Empty!");
        }
    }
}


