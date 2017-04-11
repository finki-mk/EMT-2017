package mk.ukim.finki.emt.authentication;

import mk.ukim.finki.emt.model.enums.Provider;
import mk.ukim.finki.emt.model.jpa.User;
import mk.ukim.finki.emt.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ristes on 3/15/16.
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void onAuthenticationFailure(
    HttpServletRequest httpServletRequest,
    HttpServletResponse httpServletResponse,
    AuthenticationException e
  ) throws IOException, ServletException {

    User user = userRepository.findByUsername(
      httpServletRequest.getParameter("username")
    );

    /**
     * If the user is not with local provider, try to authenticate it through
     * its provider
     */
    if (user != null && user.provider != null && user.provider != Provider.LOCAL) {
      httpServletResponse.sendRedirect(user.provider.getLoginUrl());
    }


    httpServletResponse.sendRedirect("/login?error=user not found!");
  }
}
