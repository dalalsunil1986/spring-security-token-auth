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
public class SpringSecurityFirstAppApplication implements CommandLineRunner {

	private static final Logger LOG = Logger.getLogger(SpringSecurityFirstAppApplication.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityFirstAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		LOG.info("deleting all roles from database");
//		roleRepository.deleteAll();
//		LOG.info("deleting all users from database");
//		userRepository.deleteAll();
//		LOG.info("deleting all departments from database");
//		departmentRepository.deleteAll();
//
//
//		LOG.info("inserting new user roles to database");
//		roleRepository.save(new Role("user", "ROLE_USER"));
//		roleRepository.save(new Role("rmitula", "ROLE_USER"));
//
//		Department department = new Department("Department 3", "Gdańsk");
//		LOG.info("inserting new departments to database");
//		departmentRepository.save(new Department("Department 1", "Rzeszow")); // TODO: A jak chce tu dac usera?
//		departmentRepository.save(new Department("Department 2", "Wrocław"));
//		departmentRepository.save(department);
//
//		LOG.info("inserting new users to database");
//		User ceo = User.builder().username("rmitula").password(passwordEncoder.encode("password")).firstname("Rafal").lastname("Mitula").privatePhoneNumber(664362282)
//                .businessPhoneNumber(664372927).active(true).email("rmitula@gmail.com").salary(3500).dateOfEmployment(LocalDateTime.now())
//                .dateOfEmployment(LocalDateTime.now()).lastLogin(LocalDateTime.now()).build();
//		userRepository.save(ceo);
		LOG.info(passwordEncoder.encode("password"));

//		userRepository.save(new User("rmitula", passwordEncoder.encode("password")));
//		userRepository.findAll().stream().forEach(user -> System.out.println(user.getUsername()));

		// create user without department programmaticly



	}
}
