package pl.rmitula.springsecurityfirstapp.dto;

import lombok.Getter;
import lombok.Setter;
import pl.rmitula.springsecurityfirstapp.model.User;

@Getter
@Setter
public class DepartmentDto {
    private Long id;
    private String name;
    private String city;
    private Long head;
}
