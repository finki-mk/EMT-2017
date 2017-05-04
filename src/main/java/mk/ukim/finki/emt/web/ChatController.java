package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.config.WebSocketConfig;
import mk.ukim.finki.emt.model.dto.ChatMessage;
import mk.ukim.finki.emt.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Riste Stojanov
 */
@Controller
public class ChatController {


  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");

  @Autowired
  WebSocketService webSocketService;

  @RequestMapping(value = "/send", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public void send(@RequestParam String user, @RequestParam String message) {

    ChatMessage chatMessage = new ChatMessage();
    chatMessage.user = user;
    chatMessage.message = message;
    chatMessage.time = formatter.format(LocalDateTime.now());
    webSocketService.send(WebSocketConfig.DEFAULT_TOPIC, chatMessage);
  }
}
