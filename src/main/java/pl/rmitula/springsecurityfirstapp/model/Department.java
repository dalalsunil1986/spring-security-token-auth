package pl.rmitula.springsecurityfirstapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @NonNull
    private String name;

    @NonNull
    private String city;

    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Manager manager;

    @OneToMany(mappedBy = "department")
    private Set<User> userList;

    @NonNull
    private Integer minSalary;

    @NonNull
    private Integer maxSalary;

}
