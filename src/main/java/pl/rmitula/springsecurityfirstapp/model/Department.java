package pl.rmitula.springsecurityfirstapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    // Department manager FK
    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Manager manager;

    @OneToMany
    private List<User> userList;

    public Department(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
