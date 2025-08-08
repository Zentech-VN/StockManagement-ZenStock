package service;

//import entity.ChatMessage;
//import entity.ChatSession;
import java.util.List;

public class ChatBotService {
    private OpenAIService openAIService;

    public ChatBotService() {
        this.openAIService = new OpenAIService();
    }
    
    public String sendMessage(String userMessage){   
        String assistantResponse = openAIService.sendMessage(userMessage);
        return assistantResponse;
    }
    

}
