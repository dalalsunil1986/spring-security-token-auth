package pl.rmitula.springsecurityfirstapp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
If authentication is successful, the resulting Authentication object will be placed into the SecurityContextHolder.
The configured AuthenticationSuccessHandler will then be called to either redirect or forward the user to the appropriate destination.
By default a SavedRequestAwareAuthenticationSuccessHandler is used, which means that the user will be redirected to the original destination
they requested before they were asked to login.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("cyk CustomAuthenticationSuccessHandler");
    }
}
