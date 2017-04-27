package mk.ukim.finki.emt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author Riste Stojanov
 */
@Configuration
public class SchedulerConfig {

  @Bean
  TaskScheduler taskScheduler() {

    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(20);
    taskScheduler.setThreadGroupName("emt-scheduler-");
    return taskScheduler;
  }
}
