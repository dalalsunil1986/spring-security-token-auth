package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class HelloController {

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
}
