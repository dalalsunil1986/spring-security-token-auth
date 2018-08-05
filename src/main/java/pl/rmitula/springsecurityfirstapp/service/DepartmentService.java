package pl.rmitula.springsecurityfirstapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.exception.BadRequestException;
import pl.rmitula.springsecurityfirstapp.exception.NotEmployeesFoundExcpetion;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.DepartmentRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            log.info("Returning user with id: " + id);
            return department.get();
        } else {
            throw new NotFoundException("Not found department with id: " + id);
        }
    }

    public void delete(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()) {
            if (department.get().getUserList().stream().count() == 0) {
                throw new NotEmployeesFoundExcpetion("Not found any employees in department with id: " + id);
            }
            log.info("Deleting user with id: " + id);
            departmentRepository.deleteById(id);
        } else {
            throw new NotFoundException("Not found department with id: " + id);
        }
    }

//    @Transactional
//    public Long create(String name, String city) {
//        Optional<User> headUser = userRepository.findById(headUserId);
//        if (headUser.isPresent()) {
//            //Department departmentUser = departmentRepository.findByHeadUser(headUser.get());
//            //if (departmentUser == null) {
//                log.info("Creating new department called " + name + "with headUser: " + headUser);
//                Department department = new Department();
//                department.setName(name);
//                //department.setManager(headUser.get());
//                department.setCity(city);
//                departmentRepository.save(department);
//                log.info("Creating new department:  " + department.toString());
//                return department.getId();
////            } else {
////                throw new BadRequestException("User with id " + headUserId + " is a head user of department with id " + departmentUser.getId() + " (" + departmentUser.getName() + ")");
////            }
//        } else {
//            throw new NotFoundException("Not found user with id: " + headUserId);
//        }
//    }
}
