package mk.ukim.finki.emt.events;

import org.springframework.context.ApplicationEvent;

/**
 * @author Riste Stojanov
 */
public class MailSendingErrorEvent extends ApplicationEvent {

  public MailSendingErrorEvent(Object source) {
    super(source);
  }
}
