package pl.rmitula.springsecurityfirstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.springsecurityfirstapp.model.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
