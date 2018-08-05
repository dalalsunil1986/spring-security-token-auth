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
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

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
            department.setName(department.getName());
            department.setCity(department.getCity());
            log.info("Creating new department with name: " + department.getName());
            departmentRepository.save(department);
            return department.getId();
        } else {
            throw new ConflictException("Department with this name already exists");
        }

    }

    public void update(Long id, String name, String city) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            Optional<Department> departmentByName = departmentRepository.findByName(name);
            if (departmentByName.isPresent()) {
                if (!departmentByName.get().getId().equals(id)) {
                    throw new ConflictException("Department with this name already exists");
                }
            }
            department.get().setName(name);
            department.get().setCity(city);
            log.info("Updating department with id: " + department.get().getId());
            departmentRepository.save(department.get());
        } else {
            throw new NotFoundException("Not found department with id: " + id);
        }
    }
}
