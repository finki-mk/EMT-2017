package mk.ukim.finki.emt.authentication;

import mk.ukim.finki.emt.model.jpa.User;
import mk.ukim.finki.emt.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Riste Stojanov
 */
@Service
public class StoreUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public StoreUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username);

    if (user != null) {
      List<SimpleGrantedAuthority> roles = Collections.singletonList(
        new SimpleGrantedAuthority(user.type.toString())
      );

      return new org.springframework.security.core.userdetails.User(
        user.username, user.password, roles
      );
    } else {
      return new org.springframework.security.core.userdetails.User(
        "anonymous", "", Collections.emptyList()
      );
    }
  }
}
