package pl.rmitula.springsecurityfirstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.springsecurityfirstapp.model.Department;
import pl.rmitula.springsecurityfirstapp.model.User;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
