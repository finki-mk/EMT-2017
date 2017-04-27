package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.events.UserRegisteredEvent;
import mk.ukim.finki.emt.model.jpa.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.Locale;

/**
 * Created by ristes on 4/13/16.
 */
@Service
public class UserRegistrationMailSender {


  @Autowired
  MailSender asyncMailSender;
  @Autowired
  @Qualifier("mailSenderImpl")
  MailSender syncMailSender;
  @Autowired
  JavaMailSender mailSender;

  @Autowired
  private SpringTemplateEngine templateEngine;

  public void sendRegistrationMail(User user, boolean isAsync) {

    Locale locale = Locale.getDefault();
    Context context = new Context(locale);
    context.setVariable("user", user);//"${user}"
    context.setVariable("baseUrl", "https://localhost/login");
    String content = templateEngine.process("mail/activationEmail", context);

    if (isAsync) {
      asyncMailSender.sendEmail(user, "registration completed", content, true);
    } else {
      syncMailSender.sendEmail(user, "registration completed", content, true);
    }
  }

  @EventListener(UserRegisteredEvent.class)
  public void onUserRegistration(UserRegisteredEvent event) {
    sendRegistrationMail(event.getUser(), true);
  }

}
