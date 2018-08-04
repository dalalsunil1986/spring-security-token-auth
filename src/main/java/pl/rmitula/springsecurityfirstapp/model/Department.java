package pl.rmitula.springsecurityfirstapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    private User headUser;

    @OneToMany
    private List<User> userList;

}
