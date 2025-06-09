package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSession {
    String id;
    List<ChatMessage> messages;
    Date createdAt;
    Date updatedAt;
    
    //helper method
    public void addMessage(ChatMessage message) {
        this.messages.add(message);
        this.updatedAt = new Date();
    }
    
}
