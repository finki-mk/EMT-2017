package mk.ukim.finki.emt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by ristes on 4/13/16.
 */
@Configuration
//@ImportResource("classpath:config/mail-integration.xml")
public class MailConfig {

  public static final String MAIL_PREFIX = "app.mail.";
  public static final String MAIL_PROPERTIES_PREFIX = "app.mail.properties.";
  public static final String MAIL_USERNAME = "username";
  public static final String MAIL_PASSWORD = "password";
  public static final String MAIL_PORT = "port";
  public static final String MAIL_HOST = "host";
  public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
  public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
  public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
  public static final String MAIL_DEBUG = "mail.debug";

  @Autowired
  Environment environment;

  private RelaxedPropertyResolver mailConfig;

  private RelaxedPropertyResolver mailPropertiesConfig;


  @Bean
  public JavaMailSender mailSender() {
    initPropertyResolvers();

    JavaMailSenderImpl sender = new JavaMailSenderImpl();

    sender.setUsername(mailConfig.getProperty(MAIL_USERNAME));
    sender.setPassword(mailConfig.getProperty(MAIL_PASSWORD));
    sender.setPort(mailConfig.getProperty(MAIL_PORT, Integer.class));
    sender.setHost(mailConfig.getProperty(MAIL_HOST));


    sender.setJavaMailProperties(loadProperties());
    return sender;
  }

  private void initPropertyResolvers() {
    mailConfig = new RelaxedPropertyResolver(environment, MAIL_PREFIX);
    mailPropertiesConfig = new RelaxedPropertyResolver(environment, MAIL_PROPERTIES_PREFIX);
  }

  private Properties loadProperties() {
    Properties properties = new Properties();
    properties.setProperty(MAIL_TRANSPORT_PROTOCOL, mailPropertiesConfig.getProperty(MAIL_TRANSPORT_PROTOCOL));
    properties.setProperty(MAIL_SMTP_AUTH, mailPropertiesConfig.getProperty(MAIL_SMTP_AUTH));
    properties.setProperty(MAIL_SMTP_STARTTLS_ENABLE, mailPropertiesConfig.getProperty(MAIL_SMTP_STARTTLS_ENABLE));
    properties.setProperty(MAIL_DEBUG, mailPropertiesConfig.getProperty(MAIL_DEBUG, "false"));
    return properties;
  }
}
