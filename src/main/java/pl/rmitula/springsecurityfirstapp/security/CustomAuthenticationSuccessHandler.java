package pl.rmitula.springsecurityfirstapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.rmitula.springsecurityfirstapp.model.Token;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.service.TokenService;
import pl.rmitula.springsecurityfirstapp.service.UserService;

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

//    public CustomAuthenticationSuccessHandler(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }

    //temp
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("cyk CustomAuthenticationSuccessHandler");

        //TODO: get username?
        tokenService.save(new Token(1L, "user", "tokenik"));

        httpServletResponse.addHeader("token","tokenik" );
    }
}
