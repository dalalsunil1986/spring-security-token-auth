package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.service.DepartmentService;
import pl.rmitula.springsecurityfirstapp.mapper.DtoMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public List<DepartmentDto> getDepartmentsList() {
        return departmentService.findAll().stream().map(DtoMapper::toDepartmentDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public DepartmentDto getOneDepartment(@PathVariable Long id) {
        return DtoMapper.toDepartmentDto(departmentService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.create(DtoMapper.fromDepartmentDto(departmentDto)), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public void updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) {
        departmentService.update(id, departmentDto.getName(), departmentDto.getCity(), departmentDto.getMinSalary(), departmentDto.getMaxSalary());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }
}
