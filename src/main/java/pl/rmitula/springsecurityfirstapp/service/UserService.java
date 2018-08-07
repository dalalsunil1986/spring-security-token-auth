package pl.rmitula.springsecurityfirstapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.exception.BadRequestException;
import pl.rmitula.springsecurityfirstapp.exception.ConflictException;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.Role;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.DepartmentRepository;
import pl.rmitula.springsecurityfirstapp.repository.RoleRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;
import pl.rmitula.springsecurityfirstapp.mapper.DateMapper;
import pl.rmitula.springsecurityfirstapp.util.SalaryValidator;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;



    public List<User> findAll() {
        log.info("Returning all users");
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("Returning user with id: " + id);
            return user.get();
        } else {
            throw new NotFoundException("Not found user with id: " + id);
        }
    }

    @Transactional
    // TODO: check all
    //TODO: Salary validate
    public Long create(User user, String dateOfEmployment, String lastLogin, Long departmentId, Long roleId) {
        //check unique username
        Optional<User> optionalUserByUsername = userRepository.findByUsername(user.getUsername());
        if (optionalUserByUsername.isPresent()) {
            if (!optionalUserByUsername.get().getId().equals(user.getId())) {
                throw new ConflictException("User with this username already exists");
            }
        }
        //check unique email
        Optional<User> optionalUserByEmail = userRepository.findByEmail(user.getEmail());
        if (optionalUserByEmail.isPresent()) {
            if (!optionalUserByEmail.get().getId().equals(user.getId())) {
                throw new ConflictException("User with this email already exists");
            }
        }
        //check department
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                Set<Role> roles = new HashSet<>();
                roles.add(role.get());
                user.setDateOfEmployment(DateMapper.stringToDate(dateOfEmployment));
                user.setLastLogin(DateMapper.stringToDate(lastLogin));
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setDepartment(department.get());
                user.setRoles(roles);
                System.out.println(role.get().getName());
                user.setSalary(SalaryValidator.validate(user, department.get()));
                log.info("Creating user with username: " + user.getUsername());
                return userRepository.save(user).getId();
            } else {
                throw new BadRequestException("Not found role with id: " + departmentId);
            }
        } else {
            throw new BadRequestException("Not found department with id: " + departmentId);
        }
    }

    @Transactional
    //TODO: code formatting
    public void update(Long id, User user, String dateOfEmployment, String lastLogin, Long departmentId) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            //check unique email
            Optional<User> optionalUserByEmail = userRepository.findByEmail(user.getEmail());
            if (optionalUserByEmail.isPresent()) {
                if (!optionalUserByEmail.get().getId().equals(id)) {
                    throw new ConflictException("User with this email already exists");
                }
            }
            optionalUser.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));// wrong
            optionalUser.get().setFirstname(user.getFirstname());
            optionalUser.get().setLastname(user.getLastname());
            optionalUser.get().setPrivatePhoneNumber(user.getPrivatePhoneNumber());
            optionalUser.get().setBusinessPhoneNumber(user.getBusinessPhoneNumber());
            optionalUser.get().setActive(user.isActive());
            optionalUser.get().setEmail(user.getEmail());
            optionalUser.get().setDateOfEmployment(DateMapper.stringToDate(dateOfEmployment));
            optionalUser.get().setLastLogin(DateMapper.stringToDate(lastLogin));
            Optional<Department> department = departmentRepository.findById(departmentId);
            if (department.isPresent()) {
                optionalUser.get().setSalary(cutUserSalary(id, department.get(), user));
                optionalUser.get().setDepartment(department.get());
            } else {
                throw new NotFoundException("Not found department with id: " + departmentId);
            }
            log.info("Updating user with id: " + optionalUser.get().getId());
            userRepository.save(optionalUser.get());
        } else {
            throw new NotFoundException("Not found user with id: " + id);
        }
    }

    private Integer cutUserSalary(Long id, Department department, User user) {
        if (department.getMinSalary() > user.getSalary()) {
            log.info("-- Updating salary of user with id: " + id + " from " + user.getSalary() + " to " + department.getMinSalary());
            return department.getMinSalary();
        } else if (department.getMaxSalary() < user.getSalary()) {
            log.info("-- Updating salary of user with id: " + id + " from " + user.getSalary() + " to " + department.getMaxSalary());
            return department.getMaxSalary();
        } else {
            return user.getSalary();
        }
    }

}
