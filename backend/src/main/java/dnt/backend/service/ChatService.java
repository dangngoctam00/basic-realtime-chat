package dnt.backend.service;

import dnt.backend.model.entity.Message;
import dnt.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final MessageRepository messageRepo;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatService(MessageRepository messageRepo, ChatRoomService chatRoomService) {
        this.messageRepo = messageRepo;
        this.chatRoomService = chatRoomService;
    }

    public void saveMessage(Message message) {
        this.messageRepo.save(message);
    }

    public List<Message> getMessage(String sender, String recipient) {
        Optional<String> chatIdOpt = chatRoomService.getChatId(sender, recipient, false);
        if (chatIdOpt.isPresent()) {
            return this.messageRepo.findAllByChatIdOrderByTimeDesc(chatIdOpt.get());
        }
        return Collections.emptyList();
    }
}
