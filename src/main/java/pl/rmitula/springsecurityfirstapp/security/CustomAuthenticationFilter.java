package pl.rmitula.springsecurityfirstapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import pl.rmitula.springsecurityfirstapp.model.Token;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.service.TokenService;
import pl.rmitula.springsecurityfirstapp.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("tryk CustomAuthenticationFilter");

       // final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        //get token from header
        final String accessToken = ((HttpServletRequest) servletRequest).getHeader("token");

        System.out.println(accessToken);

        Token token = tokenService.findByToken(accessToken);

        if (token != null) {
            //user
            User user = userService.findByUsername(token.getUsername());

            //auth
            final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("user", "password");
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
