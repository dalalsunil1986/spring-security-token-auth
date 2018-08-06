package pl.rmitula.springsecurityfirstapp.utils;

import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.dto.ManagerDto;
import pl.rmitula.springsecurityfirstapp.dto.UserDto;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.Manager;
import pl.rmitula.springsecurityfirstapp.model.User;

//TODO: Component (For tests)
//TODO: Split to UserMapper / DepartmentMapper, UserMapper
public class Converter {

    public static Department fromDepartmentDto(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCity(departmentDto.getCity());
        department.setMinSalary(departmentDto.getMinSalary());
        department.setMaxSalary(departmentDto.getMaxSalary());
        return department;
    }

    public static DepartmentDto toDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setCity(department.getCity());
        departmentDto.setMaxSalary(department.getMaxSalary());
        departmentDto.setMinSalary(department.getMinSalary());
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
        if (user.getDepartment() != null)
        userDto.setDepartment(user.getDepartment().getId());
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
        return user;
    }

    public static ManagerDto toManagerDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setUser(manager.getUser().getId());
        managerDto.setDepartment(manager.getDepartment().getId());
        managerDto.setStartDate(manager.getStartDate().toString());
        return managerDto;
    }
}
