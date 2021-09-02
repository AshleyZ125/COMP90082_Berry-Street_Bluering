package berryStreet.bluering.backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUser {
    private int UID;
    private String email;
    private String username;
    private String password;
    private String role;
    private String inviteCode;
}
