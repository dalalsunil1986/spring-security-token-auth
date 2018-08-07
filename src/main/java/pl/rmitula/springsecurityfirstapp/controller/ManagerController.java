package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.springsecurityfirstapp.dto.ManagerDto;
import pl.rmitula.springsecurityfirstapp.service.ManagerService;
import pl.rmitula.springsecurityfirstapp.mapper.DtoMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public List<ManagerDto> getmanagersList() {
        return managerService.findAll().stream().map(DtoMapper::toManagerDto).collect(Collectors.toList());
    }

//    @GetMapping("{id}")
//    @PreAuthorize("hasRole('ROLE_CEO')")
//    public ManagerDto getOneManager(@PathVariable Long id) {
//        return DtoMapper.toManagerDto(managerService.findOne(id));
//    }

    //FIXME: not fully tested
    @PostMapping
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> createManager(@RequestBody @Valid ManagerDto managerDto) {
        return new ResponseEntity<>(managerService.create(managerDto.getUser(), managerDto.getDepartment()), HttpStatus.CREATED);
    }
}
