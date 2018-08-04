package pl.rmitula.springsecurityfirstapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    private Integer privatePhoneNumber;

    private Integer businessPhoneNumber;

    private boolean active;

    @Email
    private String email;

    @NotNull
    private Integer salary;

    @DateTimeFormat
    private LocalDateTime dateOfEmployment;

    private LocalDateTime lastLogin;

    @OneToOne(mappedBy = "headUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Department departmentHead;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Token token;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

}