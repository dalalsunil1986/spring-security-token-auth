package pl.rmitula.springsecurityfirstapp.utils;

import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.dto.UserDto;
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

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setPrivatePhoneNumber(user.getPrivatePhoneNumber());
        userDto.setBusinessPhoneNumber(user.getBusinessPhoneNumber());
        userDto.setActive(user.isActive());
        userDto.setEmail(user.getEmail());
        userDto.setSalary(user.getSalary());
        userDto.setDateOfEmployment(user.getDateOfEmployment().toString());
        userDto.setLastLogin(user.getLastLogin().toString());
   //     userDto.setDepartment(user.getDepartment().getId());
        return userDto;
    }

    public static User fromUserDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPrivatePhoneNumber(userDto.getPrivatePhoneNumber());
        user.setBusinessPhoneNumber(userDto.getBusinessPhoneNumber());
        user.setActive(userDto.isActive());
        user.setEmail(userDto.getEmail());
        user.setSalary(userDto.getSalary());
//        user.setDateOfEmployment(userDto.getDateOfEmployment());
//        user.setLastLogin(userDto.getLastLogin());
//        user.setDepartment(userDto.getDepartment());
        return user;
    }
}
//    private Long id;
//    private String name;
//    private String city;
//    private User headUser;

//    private Long id;
//    private String username;
//    private String password;
//    private String firstname;
//    private String lastname;
//    private Integer privatePhoneNumber;
//    private Integer businessPhoneNumber;
//    private boolean active;
//    private String email;
//    private Integer salary;
//    private String dateOfEmployment;
//    private String lastLogin;
//    private Long department;