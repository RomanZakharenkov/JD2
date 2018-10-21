package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String number;
}
