package pl.rmitula.springsecurityfirstapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {
    private Long id;
    private String name;
    private String city;
    private Integer minSalary;
    private Integer maxSalary;
}
