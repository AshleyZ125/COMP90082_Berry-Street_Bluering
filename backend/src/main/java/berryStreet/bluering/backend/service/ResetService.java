package berryStreet.bluering.backend.service;

public interface ResetService {
    public void resetPassword(int UID, String password);
    public void resetEmail(int UID, String newEmail);
}
