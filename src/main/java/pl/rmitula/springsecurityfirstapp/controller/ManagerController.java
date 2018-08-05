package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.springsecurityfirstapp.dto.ManagerDto;
import pl.rmitula.springsecurityfirstapp.dto.UserDto;
import pl.rmitula.springsecurityfirstapp.model.Manager;
import pl.rmitula.springsecurityfirstapp.service.ManagerService;
import pl.rmitula.springsecurityfirstapp.utils.Converter;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public List<ManagerDto> managersList() {
        return managerService.findAll().stream().map(manager -> Converter.toManagerDto(manager)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Long> createManager(@RequestBody @Valid ManagerDto managerDto) {
        return new ResponseEntity<>(managerService.create(managerDto.getUser(), managerDto.getDepartment()), HttpStatus.CREATED);
    }
}
