package pl.rmitula.springsecurityfirstapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.service.UserService;

public class Converter {

    public static Department fromDepartmentDto(DepartmentDto departmentDto, User head) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setCity(departmentDto.getCity());
        department.setHead(head);
        return department;
    }

    public static DepartmentDto toDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setCity(department.getCity());
        departmentDto.setHead(department.getHead().getId());
        return departmentDto;
    }
}
//    private Long id;
//    private String name;
//    private String city;
//    private User head;