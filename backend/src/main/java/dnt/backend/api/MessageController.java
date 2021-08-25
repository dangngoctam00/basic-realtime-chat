package dnt.backend.api;

import dnt.backend.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@Slf4j
public class MessageController {
    @MessageMapping("/1")
    @SendTo("/channel/1")
    public Message sendMessage(Message message) {
        log.info("Controller received message: " + message.getContent());
        return message;
    }
}
