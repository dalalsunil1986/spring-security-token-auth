package pl.rmitula.springsecurityfirstapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.dto.UserDto;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.Token;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.DepartmentRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;
import pl.rmitula.springsecurityfirstapp.utils.DateMapper;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<User> findAll() {
        log.info("Returning all users");
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByToken(String accessToken) {
        return userRepository.findByToken(accessToken);
    }

    @Transactional
    public Long create(User user, String dateOfEmployment, String lastLogin, Long departmentId) {

        //TODO: Valid, Exception itp
        Optional<Department> department = departmentRepository.findById(departmentId);

        if(department.isPresent()) {
            user.setDateOfEmployment(DateMapper.stringToDate(dateOfEmployment));
            user.setLastLogin(DateMapper.stringToDate(lastLogin));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setDepartment(department.get());
            log.info("Creating user with username: " + user.getUsername());
            return userRepository.save(user).getId();

        } else {
            throw new NotFoundException("Not found department with id: " + departmentId);
        }
    }
}
