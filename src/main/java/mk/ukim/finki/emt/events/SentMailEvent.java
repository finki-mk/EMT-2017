package mk.ukim.finki.emt.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author Riste Stojanov
 */
public class SentMailEvent extends ApplicationEvent {

  public SentMailEvent(Object source) {
    super(source);
  }
}
