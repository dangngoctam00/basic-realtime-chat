package dnt.backend.api;

import dnt.backend.model.dto.MessageDto;
import dnt.backend.model.entity.ChatRoom;
import dnt.backend.model.entity.Message;
import dnt.backend.service.ChatRoomService;
import dnt.backend.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class MessageController {

    private final ChatService chatService;
    private final SimpMessagingTemplate template;
    private final ChatRoomService chatRoomService;

    @Autowired
    public MessageController(ChatService chatService, SimpMessagingTemplate template, ChatRoomService chatRoomService) {
        this.chatService = chatService;
        this.template = template;
        this.chatRoomService = chatRoomService;
    }

    @MessageMapping("/chat")
    public void sendMessage(@RequestBody MessageDto dto) {
        log.info("Controller received message: " + dto.getContent());
        String chatId = this.chatRoomService.getChatId(dto.getSender(), dto.getRecipient(), true).get();
        Message message = new Message(dto.getContent(), dto.getSender(), dto.getRecipient(), chatId);
        chatService.saveMessage(message);
        template.convertAndSendToUser(message.getRecipient(), "/queue/messages", message);
    }

    @GetMapping("/messages/{sender}/{recipient}")
    public List<MessageDto> getMessage(@PathVariable String sender, @PathVariable String recipient) {
        return this.chatService.getMessage(sender, recipient)
                .stream()
                .map(message -> new MessageDto(message.getContent(), message.getSender(), message.getRecipient(), message.getTime()))
                .collect(Collectors.toList());
    }
}
