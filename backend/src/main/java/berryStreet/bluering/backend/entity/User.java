package berryStreet.bluering.backend.entity;

import lombok.Data;

@Data
public class User {
    private int UID;
    private String email;
    private String username;
    private String password;
    private String role;
}
