package berryStreet.bluering.backend.service.imp;

import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import berryStreet.bluering.backend.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteServiceImp implements DeleteService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteUser(int UID){
        return userMapper.deleteUserByUID(UID);
    }
}
