package mk.ukim.finki.emt.authentication;

import mk.ukim.finki.emt.model.enums.Provider;
import mk.ukim.finki.emt.model.enums.UserType;
import mk.ukim.finki.emt.model.jpa.User;
import mk.ukim.finki.emt.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by ristes on 3/15/16.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  private Provider provider;
  private UserType defaultUserType;

  @Autowired
  private UserRepository userRepository;

  private User user;

  public LoginSuccessHandler(Provider provider, UserType defaultUserType) {
    this.provider = provider;
    this.defaultUserType = defaultUserType;
  }

  @Override
  public void onAuthenticationSuccess(
    HttpServletRequest httpServletRequest,
    HttpServletResponse httpServletResponse,
    Authentication authentication
  ) throws IOException, ServletException {

    HttpSession session = httpServletRequest.getSession();
    User user = getUser(authentication);
    session.setAttribute("user", user);

    super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
  }

  public User getUser(Authentication authentication) {
    User user = userRepository.findByUsername(authentication.getName());
    if (user == null) {
      user = createUserFromProvider(authentication);
    }
    return user;
  }

  private User createUserFromProvider(Authentication authentication) {


    user = new User();
    user.username = authentication.getName();
    user.type = defaultUserType;
    user.provider = provider;
    userRepository.save(user);
    return user;
  }
}
