package service;

import entity.ChatMessage;
import entity.ChatSession;
import java.util.List;

public class ChatBotService {
    private OpenAIService openAIService;
    private ChatSession currentSession;

    public ChatBotService() {
        this.openAIService = new OpenAIService();
        this.currentSession = new ChatSession();
    }
    
    public String sendMessage(String userMessage){
        //Tao tin nhan nguoi dung
        ChatMessage userChatMessage = new ChatMessage("user", userMessage);
        currentSession.addMessage(userChatMessage);
        
        //gửi tin nhắn đến OpenAI và nhận phản hồi
        String assistantResponse = openAIService.sendMessage(userMessage, currentSession.getMessages());
        
        //tạo tin nhắn phản hồi từ assistant
        ChatMessage assistantChatMessage = new ChatMessage("assistant", assistantResponse);
        currentSession.addMessage(assistantChatMessage);
        
        return assistantResponse;
    }
    
    public List<ChatMessage> getConversationHistory() {
        return currentSession.getMessages();
    }
    
    public void newSession() {
        this.currentSession = new ChatSession();
    }
}
