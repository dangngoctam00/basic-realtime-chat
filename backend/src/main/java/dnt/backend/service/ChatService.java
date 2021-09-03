package dnt.backend.service;

import dnt.backend.model.entity.Message;
import dnt.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final MessageRepository messageRepo;

    @Autowired
    public ChatService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public void saveMessage(Message message) {
        this.messageRepo.save(message);
    }

    public List<Message> getMessage(String sender, String recipient) {
        return this.messageRepo.findAllBySenderAndRecipientOrderByTimeDesc(sender, recipient);
    }
}
