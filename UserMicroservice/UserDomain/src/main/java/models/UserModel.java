package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import user.Role;

@Data
@Getter
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private Long ownerId;
}
