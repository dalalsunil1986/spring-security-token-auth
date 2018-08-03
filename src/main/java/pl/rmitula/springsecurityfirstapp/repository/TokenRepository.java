package pl.rmitula.springsecurityfirstapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmitula.springsecurityfirstapp.model.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findOneByToken(String accessToken);
}
