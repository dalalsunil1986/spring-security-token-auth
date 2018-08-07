package pl.rmitula.springsecurityfirstapp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.rmitula.springsecurityfirstapp.service.TokenService;
import pl.rmitula.springsecurityfirstapp.util.TokenGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/*
If authentication is successful, the resulting Authentication object will be placed into the SecurityContextHolder.
The configured AuthenticationSuccessHandler will then be called to either redirect or forward the user to the appropriate destination.
By default a SavedRequestAwareAuthenticationSuccessHandler is used, which means that the user will be redirected to the original destination
they requested before they were asked to login.
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${security.tokenName}")
    private String tokenName;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenGenerator tokenGenerator;

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String generatedToken = tokenGenerator.generateToken(loggedInUser.getName());
        log.info("Saving new token: " + generatedToken);
        tokenService.saveToken(loggedInUser.getName(), generatedToken);
        httpServletResponse.addHeader(tokenName, generatedToken);
    }
}
