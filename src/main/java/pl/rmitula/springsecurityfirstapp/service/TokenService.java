package pl.rmitula.springsecurityfirstapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.model.Token;
import pl.rmitula.springsecurityfirstapp.repository.TokenRepository;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public void save(Token token) {
        tokenRepository.save(token);
    }

    public boolean isTokenValid(String accessToken) {
        if (tokenRepository.findOneByToken(accessToken) != null) {
           return true;
        }
        return false;
    }

    public Token findByToken(String accessToken) {
        return tokenRepository.findOneByToken(accessToken);
    }
}
