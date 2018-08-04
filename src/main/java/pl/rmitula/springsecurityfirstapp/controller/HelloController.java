package pl.rmitula.springsecurityfirstapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rmitula.springsecurityfirstapp.model.User;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class HelloController {

    @GetMapping("/public")
    public String helloPublic() {
        return "Hello, this is public endpoint!";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloPrivate(HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hello " + user.getUsername() + " in private area!";
    }
}
