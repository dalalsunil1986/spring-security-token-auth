package pl.rmitula.springsecurityfirstapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.dto.DepartmentDto;
import pl.rmitula.springsecurityfirstapp.exception.*;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.DepartmentRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;
import pl.rmitula.springsecurityfirstapp.security.CustomUserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    HttpServletRequest request;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findOne(Long id) {
        Optional<User> user = Optional.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (!request.isUserInRole("ROLE_CEO")) {
            if(!user.get().getDepartment().getId().equals(id)) {
                throw new AccessDeniedException("Access denied");
            }
        }
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            log.info("Returning department with id: " + id);
            return department.get();
        } else {
            throw new NotFoundException("Not found department with id: " + id);
        }
    }

    public void delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()) {
            if (!department.get().getUserList().isEmpty()) {
                throw new BadRequestException("Found employees in department with id: " + id);
            }
            log.info("Deleting department with id: " + id);
            departmentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found department with id: " + id);
        }
    }

    @Transactional
    public Long create(Department department) {
        Optional<Department> departmentOptional = departmentRepository.findByName(department.getName());
        if (!departmentOptional.isPresent()) {
            if (department.getMaxSalary() < department.getMinSalary() || department.getMaxSalary() < 0 || department.getMinSalary() < 0) {
                throw new BadRequestException("Wrong salary range");
            }
            department.setName(department.getName());
            department.setCity(department.getCity());
            department.setMinSalary(department.getMinSalary());
            department.setMaxSalary(department.getMaxSalary());
            log.info("Creating new department with name: " + department.getName());
            departmentRepository.save(department);
            return department.getId();
        } else {
            throw new ConflictException("Department with this name already exists");
        }
    }

    @Transactional
    public void update(Long id, String name, String city, Integer minSalary, Integer maxSalary) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            // Checking Salary min/max
            if (maxSalary < minSalary || maxSalary < 0 || minSalary < 0) {
                throw new BadRequestException("Wrong salary range");
            }
            // Checking Department name
            Optional<Department> departmentByName = departmentRepository.findByName(name);
            if (departmentByName.isPresent()) {
                if (!departmentByName.get().getId().equals(id)) {
                    throw new ConflictException("Department with this name already exists");
                }
            }
            department.get().setName(name);
            department.get().setCity(city);
            department.get().setMinSalary(minSalary);
            department.get().setMaxSalary(maxSalary);
            // Salary update
            log.info("Checking users salary in department with id: " + id);
            Set<User> users = department.get().getUserList();
            if (!users.isEmpty()) {
                Set<User> usersToUpdate = new HashSet<>();
                for (User user : users) {
                    //TODO: Catch in function (Look UserService)
                    if (minSalary > user.getSalary()) {
                        log.info("- Updating salary of user with id: " + user.getId() + " from " + user.getSalary() + " to " + minSalary);
                        user.setSalary(department.get().getMinSalary());
                        usersToUpdate.add(user);
                    }
                    if (maxSalary < user.getSalary()) {
                        log.info("- Updating salary of user with id: " + user.getId() + " from " + user.getSalary() + " to " + maxSalary);
                        user.setSalary(maxSalary);
                        usersToUpdate.add(user);
                    }
                }
                userRepository.saveAll(usersToUpdate);
            } else {
                log.info("- Updating users salaries not needed");
            }
            log.info("Updating department with id: " + department.get().getId());
            departmentRepository.save(department.get());
        } else {
            throw new NotFoundException("Not found department with id: " + id);
        }
    }
}
