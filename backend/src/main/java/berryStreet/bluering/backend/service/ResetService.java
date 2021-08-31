package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetService {
    @Autowired
    private UserMapper userMapper;

    public void resetPassword(int UID, String password){
        boolean result=userMapper.resetPassword(UID, password);
        if(result==false){
            resetPassword(email, password);
        }
    }
    public void resetEmail(int UID, String newEmail){
        boolean result=userMapper.resetEmail(UID, newEmail);
        if(result==false){
            resetEmail(UID, password);
        }
    }


}
