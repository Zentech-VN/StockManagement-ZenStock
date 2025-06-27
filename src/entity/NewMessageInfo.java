package entity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NewMessageInfo {
    
    public String message = null;
    public String messageTime = "";     
    public int total = 0;            
    

    public NewMessageInfo() {

    }
    
    public NewMessageInfo(String message, String messageTime, int total) {
        this.message = message;
        this.messageTime = messageTime;
        this.total = total;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessageTime() {
        return messageTime;
    }
    
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    

    public static String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM,yyyy");
        return dateFormatter.format(date);
    }
    
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm aaa");
        return timeFormatter.format(date);
    }
    
    public boolean hasUnreadMessages() {
        return total > 0;
    }
    
    public boolean hasMessage() {
        return message != null && !message.trim().isEmpty();
    }
    
    public String getDisplayMessage() {
        if (hasMessage()) {
            return message;
        }
        return "No messages yet";
    }
    
    public String getDisplayTime() {
        if (messageTime != null && !messageTime.trim().isEmpty()) {
            return messageTime;
        }
        return "";
    }
    
    public String getUnreadCountText() {
        if (total > 0) {
            return String.valueOf(total);
        }
        return "";
    }
    
    public String getUnreadCountLabel() {
        if (total == 0) {
            return "No unread messages";
        } else if (total == 1) {
            return "1 unread message";
        } else {
            return total + " unread messages";
        }
    }
    
    public void reset() {
        this.message = null;
        this.messageTime = "";
        this.total = 0;
    }
    
    public void incrementTotal() {
        this.total++;
    }
    
    public void setMessageAndTime(String message, String messageTime) {
        this.message = message;
        this.messageTime = messageTime;
    }
    
    @Override
    public String toString() {
        return "NewMessageInfo{" +
                "message='" + message + '\'' +
                ", messageTime='" + messageTime + '\'' +
                ", total=" + total +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        NewMessageInfo that = (NewMessageInfo) obj;
        
        if (total != that.total) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return messageTime != null ? messageTime.equals(that.messageTime) : that.messageTime == null;
    }
    
    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        result = 31 * result + total;
        return result;
    }
}
