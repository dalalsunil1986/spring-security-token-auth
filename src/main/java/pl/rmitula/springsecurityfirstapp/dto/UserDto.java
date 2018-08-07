package pl.rmitula.springsecurityfirstapp.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private Integer privatePhoneNumber;
    @NotNull
    private Integer businessPhoneNumber;
    private boolean active;
    @NotNull
    private String email;
    @NotNull
    private Integer salary;
    @NotNull
    private String dateOfEmployment;
    @NotNull
    private String lastLogin;
    @NotNull
    private Long role;
    @NotNull
    private Long department;
}
