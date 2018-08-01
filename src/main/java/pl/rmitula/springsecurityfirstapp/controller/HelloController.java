package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;
import pl.rmitula.springsecurityfirstapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/public")
    public String helloPublic() {
        return "Hello, this is public endpoint!";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloPrivate(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return "Hello " + principal.getName() + " in private area!";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<User> usersList() {
        return userService.findAll();
    }
}
