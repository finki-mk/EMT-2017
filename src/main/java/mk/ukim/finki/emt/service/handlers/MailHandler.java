package mk.ukim.finki.emt.service.handlers;

import mk.ukim.finki.emt.events.MailSendingErrorEvent;
import mk.ukim.finki.emt.events.SentMailEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Riste Stojanov
 */
@Component
public class MailHandler {

  @EventListener(SentMailEvent.class)
  public void onSuccess(SentMailEvent event) {
    System.out.println("Mail successfully sent");
  }


  @EventListener(MailSendingErrorEvent.class)
  public void onError(MailSendingErrorEvent event) {
    System.out.println("Mail not sent. Error occurred!");
  }

}
