package pl.rmitula.springsecurityfirstapp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Slf4j
public class CustomAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private TokenService tokenService;

    @Value("${security.tokenName}")
    private String tokenName;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final String accessToken = ((HttpServletRequest) servletRequest).getHeader(tokenName);
        Token token = tokenService.findByToken(accessToken);
        if (token != null) {
            User user = token.getUser();
            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            log.info("Authenticated user with id: " + user.getId() + " " + customUserDetails.getAuthorities().toString());
            final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
