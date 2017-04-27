package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.jpa.User;

/**
 * Created by ristes on 4/13/16.
 */
public interface MailSender {

  void sendEmail(User receiver, String subject, String content, boolean isHtml);

}
