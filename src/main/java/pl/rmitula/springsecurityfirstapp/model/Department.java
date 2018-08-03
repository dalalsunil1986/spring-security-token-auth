package pl.rmitula.springsecurityfirstapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String city;

    @OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "head_id")
    private User head;

    @OneToMany
    private List<User> userList;

    public Department(String name, String city) {
        this.name = name;
        this.city = city;
    }
}
