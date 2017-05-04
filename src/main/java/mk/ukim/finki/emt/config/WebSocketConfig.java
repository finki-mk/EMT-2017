package mk.ukim.finki.emt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.SockJsServiceRegistration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

  public static final String TOPIC_PREFIX = "/room";
  public static final String DEFAULT_TOPIC = "/emt";
  public static final String BROKER_PREFIX = "/broker";
  public static final String SOCKET_URL = "/chat";


  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // ws sending url prefix (Topic Url) -> When sending data to the client
    config.enableSimpleBroker(TOPIC_PREFIX);
    // listening url prefix (Broker Url) -> When receiving data from the client
    config.setApplicationDestinationPrefixes(BROKER_PREFIX);

  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // socket connection url
    SockJsServiceRegistration reg = registry
      .addEndpoint(SOCKET_URL)
      .withSockJS();
  }

}
