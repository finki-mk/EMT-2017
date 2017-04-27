package mk.ukim.finki.emt.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by ristes on 4/20/16.
 */
@Component
public class NewsletterTask {


  @Autowired
  private TaskScheduler taskScheduler;

  @Scheduled(cron = "20 * * * * ?")
  public void sendNewsletter() {
    System.out.println("Sending newsletter");
  }

  @Scheduled(fixedRate = 20000, initialDelay = 1000)
  public void executeOnEvery20seconds() {
    System.out.println("Started test1");
  }

  @PostConstruct
  public void dynamicCronRegistration() {
    String the20thSecondOfEveryMinute = "20 * * * * ?";
    CronTrigger trigger = new CronTrigger(the20thSecondOfEveryMinute);
    System.out.println("Registering dynamic execution");
    taskScheduler.schedule(
      () -> System.out.println("Dynamically scheduled task"),
      trigger
    );
  }
}
