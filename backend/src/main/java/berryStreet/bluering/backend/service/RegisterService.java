package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserMapper userMapper;

    public int register(User user) {
        User userQueried = userMapper.queryUserByEmail(user.getEmail());
        if (userQueried != null) {
            return Constant.EMAIL_EXIST;
        }
        int result = userMapper.insertUser(user);
        return result == 0 ? Constant.REGISTER_FAIL : Constant.REGISTER_SUCCESS;
    }


}
