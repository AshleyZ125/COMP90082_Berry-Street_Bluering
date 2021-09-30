package berryStreet.bluering.backend.service;

import berryStreet.bluering.backend.entity.User;

public interface ResetService {
    public int resetPassword(int UID, String password);
    public int resetEmail(int UID, String newEmail);
    public User queryUserByEmail(String email);
    public User queryUserByUID(int UID);
}
