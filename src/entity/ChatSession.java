/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Duc Pham Ngoc
 */
public class ChatSession {
    private String id;
    private List<ChatMessage> messages;
    private Date createdAt;
    private Date updatedAt;

    public ChatSession() {
        this.messages = new ArrayList<>();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public String getId() {
        return id;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    
    //helper method
    public void addMessage(ChatMessage message) {
        this.messages.add(message);
        this.updatedAt = new Date();
    }
    
}
