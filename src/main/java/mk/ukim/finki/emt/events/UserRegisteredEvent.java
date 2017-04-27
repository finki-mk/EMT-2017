package mk.ukim.finki.emt.events;

import mk.ukim.finki.emt.model.jpa.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author Riste Stojanov
 */
public class UserRegisteredEvent extends ApplicationEvent {
  public UserRegisteredEvent(User user) {
    super(user);
  }

  public User getUser() {
    return (User) getSource();
  }

}
