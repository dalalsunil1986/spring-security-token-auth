package pl.rmitula.springsecurityfirstapp.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDto {
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String city;
    @NonNull
    private Integer minSalary;
    @NonNull
    private Integer maxSalary;
}
