package berryStreet.bluering.backend.controller;


import berryStreet.bluering.backend.Utils.AjaxResult;
import berryStreet.bluering.backend.entity.RegisterUser;
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

    //@PostMapping("/api/user/superLogin")
    //public AjaxResult superLogin(@RequestBody User user) { return login(user,"supervisor"); }
    //@PostMapping("/api/user/lecLogin")
    //public AjaxResult lecLogin(@RequestBody User user) { return login(user,"LEC"); }

    @PostMapping("/api/user/login")
    private AjaxResult login(@RequestBody User user){
        System.out.println("login start,user: "+ user.toString());
        if (user.getEmail() != null && user.getPassword() !=null) {
            User result = loginService.checkUserExist(user);
            if (result != null) {
                System.out.println(result.toString());
                if(result.getUID()>0)
                    return AjaxResult.success(result);
                else{
                    return AjaxResult.warn(" Login fail! Please check your password!");
                }
            } else {
                return AjaxResult.warn("The user does not exist! Please check your email!");
            }
        } else {
            return AjaxResult.error("Input Empty!");
        }
    }

    @PostMapping("/api/user/register")
    public AjaxResult register(@RequestBody RegisterUser user) {
        int result = registerService.register(user);
        switch (result) {
            case 0:
                return AjaxResult.success();
            case 1:
                return AjaxResult.error("register failed,please retry");
            case 2:
                return AjaxResult.error("email exist");
            case 3:
                return AjaxResult.error("wrong invite code");
            default:
                return AjaxResult.error("unknown error,please retry");
        }
    }

    @PostMapping("/api/user/superResetPassword")
    public AjaxResult superResetPassword(@RequestBody Map<String, Object> map) {
        if (map != null) {
            User userQueried = resetService.queryUserByUID((int)map.get("UID"));
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
    @PostMapping("/api/user/superResetEmail")
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

    @PostMapping("/api/user/resetUsername")
    public AjaxResult resetUsername(@RequestBody User user){
        if(user.getUID()==0)
            return AjaxResult.error("Input Empty!");
        User checkUser=resetService.queryUserByUID(user.getUID());
        if(checkUser.getUID()==0){
            return AjaxResult.warn("The user does not exist!");
        }else{
            int result=resetService.resetUsername(user.getUID(),user.getUsername());
            if (result != 0)
                return AjaxResult.success("Successful reset!");
            else
                return AjaxResult.error("Reset fail!");
        }

    }

    @PostMapping("/api/user/delete")
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


