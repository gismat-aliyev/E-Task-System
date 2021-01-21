package az.com.etask.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Role role;
    private Company company;
}
