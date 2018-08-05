package pl.rmitula.springsecurityfirstapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.Manager;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.DepartmentRepository;
import pl.rmitula.springsecurityfirstapp.repository.ManagerRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;
import pl.rmitula.springsecurityfirstapp.utils.DateMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Long create(Long userId, Long departmentId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Department> department = departmentRepository.findById(departmentId);
            if (department.isPresent()) {
                Manager manager = new Manager();
                manager.setUser(user.get());
                manager.setDepartment(department.get());
                manager.setStartDate(LocalDateTime.now());
                log.info("Creating new manager: userId: " + userId + ", departmentId: " + departmentId);
                return managerRepository.save(manager).getId();
            } else {
                throw new NotFoundException("Not found department with id: " + departmentId);
            }
        } else {
            throw new NotFoundException("Not found user with id: " + userId);
        }
    }
}
