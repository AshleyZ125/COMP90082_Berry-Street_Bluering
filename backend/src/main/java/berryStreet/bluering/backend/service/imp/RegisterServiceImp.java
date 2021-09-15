package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.config.AdminConfig;
import berryStreet.bluering.backend.entity.RegisterUser;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import berryStreet.bluering.backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RegisterServiceImp implements RegisterService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminConfig adminConfig;
    @Override
    public int register(RegisterUser registerUser) {
        User userQueried = userMapper.queryUserByEmail(registerUser.getEmail());
        if (userQueried != null) {
            return Constant.EMAIL_EXIST;
        }
        if(registerUser.getRole().equals("supervisor")){
            Set<String> inviteCodes  = adminConfig.getInviteCodes();
            if(!inviteCodes.contains(registerUser.getCode())){
                return Constant.WRONG_INVITECODE;
            }
        }
        User user = User.builder().username(registerUser.getUsername())
                .email(registerUser.getEmail()).password(registerUser.getPassword())
                .role(registerUser.getRole()).build();
        int result = userMapper.insertUser(user);
        return result == 0 ? Constant.REGISTER_FAIL : Constant.REGISTER_SUCCESS;
    }
}
