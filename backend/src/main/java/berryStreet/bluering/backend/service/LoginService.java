package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.User;

public interface LoginService {
    public User checkUserExist(User user);
}
