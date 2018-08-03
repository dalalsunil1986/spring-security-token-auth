package pl.rmitula.springsecurityfirstapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.exception.NotEmployeesFoundExcpetion;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.DepartmentRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findOne(Long id) {
        Department department = departmentRepository.getOne(id);
        //TODO: exception handling
        return department;
    }

    public void delete(Long id) {
        Department department = departmentRepository.getOne(id);

        if(department != null) {

            if (department.getUserList().stream().count() == 0) {
                throw new NotEmployeesFoundExcpetion("Not found any employees in department with id: " + id);
            }

            departmentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found department with id: " + id);
        }
    }

    @Transactional
    public Long create(String name, String city, Long head) {
        //TODO: RETURN CREATED!
        //TODO: Add more logic here
        User headUser = userRepository.getOne(head);
        //FIXME: Getting Unable to find pl.rmitula.springsecurityfirstapp.model.User with id 1", with unknown user id provided
        if (headUser != null) {
            Department departmentUser = departmentRepository.findByHead(headUser);

            if (departmentUser == null) {
                log.info("Creating new department called " + name + "with head: " + headUser);
                Department department = new Department();
                department.setName(name);
                department.setHead(headUser);
                department.setCity(city);
                departmentRepository.save(department);
            } else {
                //TODO: Ask if needed?
                throw new NotFoundException("User with id " + head + " is a head of department with id: " + departmentUser.getId());
            }
        } else {
            throw new NotFoundException("Not found user (department head) with id: " + head);
        }


        return null;

    }
}
