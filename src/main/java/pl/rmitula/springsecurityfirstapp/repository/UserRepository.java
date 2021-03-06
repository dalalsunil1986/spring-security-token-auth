package pl.rmitula.springsecurityfirstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.springsecurityfirstapp.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByToken(String accessToken);

    Optional<User> findByEmail(String email);
}
