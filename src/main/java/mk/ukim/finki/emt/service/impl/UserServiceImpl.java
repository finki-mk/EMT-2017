package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.model.enums.Provider;
import mk.ukim.finki.emt.model.enums.UserType;
import mk.ukim.finki.emt.model.jpa.User;
import mk.ukim.finki.emt.persistence.UserRepository;
import mk.ukim.finki.emt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Riste Stojanov
 */
@Service
public class UserServiceImpl implements UserService {

  private BCryptPasswordEncoder passwordEncoder;
  private UserRepository userRepository;
  private Environment environment;


  @Autowired
  public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, Environment environment) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.environment = environment;
  }

  @Override
  public User createCustomer(String username, String password) {
    return null;
  }

  @Override
  public User createAdmin(String username, String password) {
    User user = new User();
    user.username = username;
    user.password = encryptPassword(password);
    user.type = UserType.ROLE_ADMIN;
    user.provider = Provider.LOCAL;

    return userRepository.save(user);
  }

  private String encryptPassword(String password) {
    return passwordEncoder.encode(password);
  }


  @PostConstruct
  public void init() {
    if (environment.getProperty("store.security.defaultAdmin.create", Boolean.class, false)) {

      if (userRepository.count() == 0) {
        createAdmin(
          environment.getProperty("store.security.defaultAdmin.username"),
          environment.getProperty("store.security.defaultAdmin.password")
        );
      }
    }

  }
}
