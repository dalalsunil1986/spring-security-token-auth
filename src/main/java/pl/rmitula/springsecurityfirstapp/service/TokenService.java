package pl.rmitula.springsecurityfirstapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.model.Token;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.TokenRepository;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String generateAndSaveToken(String username) {
        //TODO: Generate tokens in another class
        String myToken = passwordEncoder.encode(username);
        Token  token = new Token();
        token.setToken(myToken);
        token.setUsername(username);
        tokenRepository.save(token);
        return myToken;
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
