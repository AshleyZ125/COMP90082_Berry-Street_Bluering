package berryStreet.bluering.backend.controller;


import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import berryStreet.bluering.backend.service.RegisterService;
import berryStreet.bluering.backend.service.ResetService;
import berryStreet.bluering.backend.service.DeleteService;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ResetService resetService;
    @Autowired
    private DeleteService deleteService;

    @PostMapping("/superLogin")
    public AjaxResult superLogin(@RequestBody Map<String, Object> map) {
        return login(map,"supervisor");
    }

    @PostMapping("/lecLogin")
    public AjaxResult superLogin(@RequestBody Map<String, Object> map) {
        return login(map,"LEC");
    }

    private AjaxResult login(@RequestBody Map<String, Object> map, String role){
        if (map != null) {
            User user = new User();
            user.setEmail(map.get("email") + "");
            user.setPassword(map.get("password") + "");
            user.setRole(role);
            User result = loginService.checkUserExist(user);
            if (result.getUid() != null) {
                return AjaxResult.success(result);
            } else {
                return AjaxResult.warn("The user does not exist!");
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

    @PostMapping("/superResetPassword")
    public AjaxResult superResetPassword(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User userQueried = userMapper.queryUserByEmail(map.get("email") + "");
            if(userQueried!=null){
                if(userQueried.getPassword().equals(map.get("oldPassword") + "")){
                    resetService.resetPassword(userQueried.getUID(), map.get("newPassword") + "");
                    return AjaxResult.success("Reset password successfully");
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
    @PostMapping("/superResetEmail")
    public AjaxResult superResetEmail(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User userQueried = userMapper.queryUserByUID((int)map.get("UID"));
            if(userQueried!=null){
                if(userMapper.queryUserByUID(map.get("newEmail")+"")!=null){
                    return AjaxResult.warn("New email has been bound!");
                }else{
                    resetService.resetEmail(userQueried.getUID(), map.get("newEmail")+"");
                    return AjaxResult.success("Reset email successfully");
                }
            }else{
                return AjaxResult.warn("The user does not exist!");
            }
        } else {
            return AjaxResult.error("Input Empty!");
        }
    }
    @PostMapping("/superDelete")
    public AjaxResult superDelete(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User userQueried = userMapper.queryUserByUID((int)map.get("UID"));
            if(userQueried!=null){
                deleteService.deleteUser(userQueried.getUID());
                return AjaxResult.success("Delete user successfully");
            }else{
                return AjaxResult.warn("The user does not exist!");
            }
        } else {
            return AjaxResult.error("Input Empty!");
        }
    }
}


