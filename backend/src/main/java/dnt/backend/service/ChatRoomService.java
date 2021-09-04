package dnt.backend.service;

import dnt.backend.model.entity.ChatRoom;
import dnt.backend.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepo;

    @Autowired
    public ChatRoomService(ChatRoomRepository chatRoomRepo) {
        this.chatRoomRepo = chatRoomRepo;
    }

    public Optional<String> getChatId(String sender, String recipient, boolean isCreatedIfNotFound) {
         Optional<ChatRoom> chatRoomOpt = chatRoomRepo.findChatRoomBySenderAndRecipient(sender, recipient);
         if (chatRoomOpt.isPresent()) {
             String chatId = chatRoomOpt.get().getChatId();
             return Optional.of(chatId);
         }
         else {
             if (!isCreatedIfNotFound) {
                 return Optional.empty();
             }
             String chatId = String.format("%s-%s", sender, recipient);
             ChatRoom roomOnSender = new ChatRoom(sender, recipient, chatId);
             ChatRoom roomOnRecipient = new ChatRoom(recipient, sender, chatId);
             chatRoomRepo.save(roomOnSender);
             chatRoomRepo.save(roomOnRecipient);
             return Optional.of(chatId);
         }
    }
}
