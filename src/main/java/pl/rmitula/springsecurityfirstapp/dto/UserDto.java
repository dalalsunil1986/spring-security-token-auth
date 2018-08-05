package pl.rmitula.springsecurityfirstapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private Integer privatePhoneNumber;
    private Integer businessPhoneNumber;
    private boolean active;
    private String email;
    private Integer salary;
    private String dateOfEmployment;
    private String lastLogin;
    private Long department;
}
