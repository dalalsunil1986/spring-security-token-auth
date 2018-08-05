package pl.rmitula.springsecurityfirstapp.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

}
