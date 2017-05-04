package mk.ukim.finki.emt.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by ristes on 4/14/16.
 */
public class EmailReceiverService {

  public void receive(MimeMessage mimeMessage) throws MessagingException {
    System.out.println("recevied mail from: " + mimeMessage.getFrom()[0].toString());
  }
}
