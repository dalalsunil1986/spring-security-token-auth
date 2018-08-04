package pl.rmitula.springsecurityfirstapp.utils;

import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.User;

public class Converter {

    public static Department fromDepartmentDto(DepartmentDto departmentDto, User head) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setCity(departmentDto.getCity());
        department.setHeadUser(head);
        return department;
    }

    public static DepartmentDto toDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setCity(department.getCity());
        departmentDto.setHeadUser(department.getHeadUser().getId());
        return departmentDto;
    }
}
//    private Long id;
//    private String name;
//    private String city;
//    private User headUser;