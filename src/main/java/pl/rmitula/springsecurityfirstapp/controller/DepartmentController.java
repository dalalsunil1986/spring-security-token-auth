package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.exception.NotEmployeesFoundExcpetion;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.service.DepartmentService;
import pl.rmitula.springsecurityfirstapp.service.UserService;
import pl.rmitula.springsecurityfirstapp.utils.Converter;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private UserService userService;

    @GetMapping
    public List<DepartmentDto> getDepartmentsList() {
        return departmentService.findAll().stream().map(department -> Converter.toDepartmentDto(department)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public DepartmentDto getOneDepartment(@PathVariable Long id) {
        return Converter.toDepartmentDto(departmentService.findOne(id));
    }

    @PostMapping
    public Long createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        return departmentService.create(departmentDto.getName(), departmentDto.getCity(), departmentDto.getHead());
    }

    @DeleteMapping("{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }

}
