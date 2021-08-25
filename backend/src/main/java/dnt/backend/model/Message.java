package dnt.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String content;
    private String user;

    public Message(String content, String user) {
        this.content = content;
        this.user = user;
    }
}
