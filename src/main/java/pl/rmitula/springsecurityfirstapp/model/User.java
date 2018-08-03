package pl.rmitula.springsecurityfirstapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @OneToOne(mappedBy = "head", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Department departmentHead;

    @ManyToOne
    @JoinColumn(name="department_id")
    //TODO: Add nullable
    private Department department;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}