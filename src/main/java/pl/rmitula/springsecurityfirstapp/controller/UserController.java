package pl.rmitula.springsecurityfirstapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.dto.UserDto;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.service.UserService;
import pl.rmitula.springsecurityfirstapp.utils.Converter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/public")
//    public String helloPublic() {
//        return "Hello, this is public endpoint!";
//    }
//
//    @GetMapping("/private")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String helloPrivate(HttpServletRequest request) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return "Hello " + user.getUsername() + " in private area!";
//    }

    @GetMapping
    public List<UserDto> usersList() {
        return userService.findAll().stream().map(user -> Converter.toUserDto(user)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.create(Converter.fromUserDto(userDto), userDto.getDateOfEmployment(), userDto.getLastLogin(), userDto.getDepartment()), HttpStatus.CREATED);
    }
}
