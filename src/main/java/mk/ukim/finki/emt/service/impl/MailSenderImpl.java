package mk.ukim.finki.emt.service.impl;

import mk.ukim.finki.emt.events.MailSendingErrorEvent;
import mk.ukim.finki.emt.events.SentMailEvent;
import mk.ukim.finki.emt.model.jpa.User;
import mk.ukim.finki.emt.service.MailSender;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Created by ristes on 4/13/16.
 */
@Service
public class MailSenderImpl implements MailSender {

  @Autowired
  ApplicationEventPublisher publisher;
  @Autowired
  private JavaMailSender mailSender;

  @Override
  public void sendEmail(User user, String subject, String content, boolean isHtml) {
    final String to = user.email;
    final String from = "contact@emt-store.com";

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      MimeMessageHelper message =
        new MimeMessageHelper(
          mimeMessage, false, CharEncoding.UTF_8);
      message.setTo(to);
      message.setFrom(from);
      message.setSubject(subject);
      message.setText(content, isHtml);
      mailSender.send(mimeMessage);
      publisher.publishEvent(new SentMailEvent(user));
    } catch (Exception e) {
      e.printStackTrace();
      publisher.publishEvent(new MailSendingErrorEvent(user));
    }
  }
}
