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
    private String code;

    public RegisterUser(int UID, String email, String username, String password, String role, String code) {
        this.UID = UID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.code = code;
    }

    public RegisterUser() {
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
