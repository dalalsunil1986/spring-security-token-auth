package pl.rmitula.springsecurityfirstapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.springsecurityfirstapp.dto.UserDto;
import pl.rmitula.springsecurityfirstapp.service.UserService;
import pl.rmitula.springsecurityfirstapp.mapper.DtoMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    //TODO: Allow manager to check his own employees in department

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CEO')")
    public List<UserDto> getUsersList() {
        return userService.findAll().stream().map(DtoMapper::toUserDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public UserDto getOneUser(@PathVariable Long id) {
        return DtoMapper.toUserDto(userService.findOne(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CEO')")
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.create(DtoMapper.fromUserDto(userDto), userDto.getDateOfEmployment(), userDto.getLastLogin(), userDto.getDepartment(), userDto.getRole()), HttpStatus.CREATED);
    }

    //TODO: Refactor for salary
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_CEO')")
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        userService.update(id, DtoMapper.fromUserDto(userDto), userDto.getDateOfEmployment(), userDto.getLastLogin(), userDto.getDepartment());
    }

}
