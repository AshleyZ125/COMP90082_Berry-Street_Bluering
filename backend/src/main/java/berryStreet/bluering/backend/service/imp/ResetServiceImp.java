package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import berryStreet.bluering.backend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetServiceImp implements ResetService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int resetPassword(int UID, String password){
        return userMapper.resetPassword(UID, password);
    }
    @Override
    public int resetEmail(int UID, String newEmail){
        return userMapper.resetEmail(UID, newEmail);
    }
}
