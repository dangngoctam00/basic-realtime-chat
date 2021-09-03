package dnt.backend.api;

import dnt.backend.model.dto.MessageDto;
import dnt.backend.model.entity.Message;
import dnt.backend.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class MessageController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public MessageController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void sendMessage(@RequestBody MessageDto dto) {
        log.info("Controller received message: " + dto.getContent());
        Message message = new Message(dto.getContent(), dto.getSender(), dto.getRecipient());
        chatService.saveMessage(message);
        messagingTemplate.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
    }
}
