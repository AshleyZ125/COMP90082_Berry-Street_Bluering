package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import berryStreet.bluering.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkUserExist(User user) {
        User result=userMapper.queryUserByEmail(user.getEmail());
        if(result==null)return null;
        if(user.getPassword().equals(result.getPassword())){
            result.setPassword(null);
        }else{
            result.setUID(-1);
        }
        return result;
    }
}
