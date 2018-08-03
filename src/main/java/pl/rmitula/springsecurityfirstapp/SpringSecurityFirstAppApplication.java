package pl.rmitula.springsecurityfirstapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.rmitula.springsecurityfirstapp.model.Role;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.RoleRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityFirstAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityFirstAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		userRepository.save(new User("user", passwordEncoder.encode("password")));
		userRepository.save(new User("rmitula", passwordEncoder.encode("password")));
		userRepository.findAll().stream().forEach(user -> System.out.println(user.getUsername()));

		roleRepository.deleteAll();
		roleRepository.save(new Role("user", "ROLE_USER"));
		roleRepository.save(new Role("rmitula", "ROLE_USER"));
	}
}
