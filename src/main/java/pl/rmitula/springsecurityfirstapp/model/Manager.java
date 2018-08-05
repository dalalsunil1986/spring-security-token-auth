package pl.rmitula.springsecurityfirstapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "managers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Manager {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Department department;

    private LocalDateTime startDate;

}
