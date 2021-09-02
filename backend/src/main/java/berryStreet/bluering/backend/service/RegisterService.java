package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.Constant.Constant;
import berryStreet.bluering.backend.entity.RegisterUser;
import berryStreet.bluering.backend.entity.User;
import berryStreet.bluering.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface RegisterService {
    public int register(RegisterUser user);
}
