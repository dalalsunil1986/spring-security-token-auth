package pl.rmitula.springsecurityfirstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.springsecurityfirstapp.model.Manager;
import pl.rmitula.springsecurityfirstapp.model.User;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUser(User user);
}
