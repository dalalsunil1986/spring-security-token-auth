package pl.rmitula.springsecurityfirstapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.exception.NotFoundException;
import pl.rmitula.springsecurityfirstapp.model.Token;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.TokenRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;

import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveToken(String username, String generatedToken) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Token token = new Token();
            token.setToken(generatedToken);
            token.setUser(user.get());
            tokenRepository.save(token);
        } else {
            throw new NotFoundException("Not found user with username: " + username);
        }
    }

    public Token findByToken(String accessToken) {
        return tokenRepository.findOneByToken(accessToken);
    }
}
