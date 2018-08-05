package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.service.DepartmentService;
import pl.rmitula.springsecurityfirstapp.utils.Converter;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDto> getDepartmentsList() {
        return departmentService.findAll().stream().map(department -> Converter.toDepartmentDto(department)).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public DepartmentDto getOneDepartment(@PathVariable Long id) {
        return Converter.toDepartmentDto(departmentService.findOne(id));
    }

//    @PostMapping
//    public ResponseEntity<Long> createDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
//        return new ResponseEntity<>(departmentService.create(departmentDto.getName(), departmentDto.getCity(), HttpStatus.CREATED);
//    }

    @DeleteMapping("{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
    }

}
