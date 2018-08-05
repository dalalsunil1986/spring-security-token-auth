package pl.rmitula.springsecurityfirstapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.Role;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.DepartmentRepository;
import pl.rmitula.springsecurityfirstapp.repository.RoleRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;
import pl.rmitula.springsecurityfirstapp.security.CustomAuthenticationSuccessHandler;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@SpringBootApplication
public class SpringSecurityFirstAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityFirstAppApplication.class, args);
	}
}
