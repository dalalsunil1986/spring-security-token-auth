package pl.rmitula.springsecurityfirstapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.security.CustomAuthenticationFailureHandler;
import pl.rmitula.springsecurityfirstapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping("/public")
    public String helloPublic() {
        LOG.info("GET: /public");
        return "Hello, this is public endpoint!";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String helloPrivate(HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.info("GET: /private by user:" + user.getUsername());
        return "Hello " + user.getUsername() + " in private area!";
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<User> usersList() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LOG.info("GET: /users by user:" + user.getUsername());
        return userService.findAll();
    }
}
