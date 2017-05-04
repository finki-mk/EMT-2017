package mk.ukim.finki.emt.service;

/**
 * Created by ristes on 7/21/15.
 */
public interface WebSocketService {

  void send(String topic, Object data, String... topicParams);

  void sendToUser(String user, String topic, Object data, String... topicParams);
}
