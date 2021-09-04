package dnt.backend.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String sender;
    private String recipient;
    private String chatId;
    private Instant time;

    public Message(String content, String sender, String recipient, String chatId) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.chatId = chatId;
        this.time = Instant.now();
    }
}
