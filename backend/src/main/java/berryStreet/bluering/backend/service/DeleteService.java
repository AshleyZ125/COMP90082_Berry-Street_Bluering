package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    @Autowired
    private UserMapper userMapper;

    public void deleteUser(int UID){
        boolean result=userMapper.deleteUserByUID(UID);
        if(result==false){
            deleteUser(UID);
        }
    }

}
