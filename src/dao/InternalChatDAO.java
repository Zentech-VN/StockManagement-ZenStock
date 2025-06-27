package dao;

import entity.NewMessageInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import entity.InternalChatMessage;
import java.sql.SQLException;
import jdbc.ConnectionHelper;

public class InternalChatDAO {
    
    private Connection connection;
    
    public InternalChatDAO() throws SQLException {
        this.connection = ConnectionHelper.getConnection();
    }
    
    public int saveMessage(InternalChatMessage message) {
        int result = 0;
        try {
            String query = "INSERT INTO nhantin (nguoiguiid, nguoiguiten, nguoinhanid, noidung, giogui, ngaygui, daxemboi) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, message.getNguoiGuiId());
            pr.setString(2, message.getNguoiGuiTen());
            pr.setString(3, message.getNguoiNhanId());
            pr.setString(4, message.getNoiDung());
            pr.setString(5, message.getGioGui());
            pr.setString(6, message.getNgayGui());
            pr.setString(7, ""); 
            result = pr.executeUpdate();
            pr.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }

    public ArrayList<InternalChatMessage> getAllMessages() {
        ArrayList<InternalChatMessage> list = new ArrayList<InternalChatMessage>();
        try {
            String query = "SELECT * FROM nhantin ORDER BY matin ASC";
            Statement pr = connection.createStatement();
            ResultSet rs = pr.executeQuery(query);
            while (rs.next()) {
                InternalChatMessage message = new InternalChatMessage();
                message.setMaTin(rs.getInt("matin"));
                message.setNguoiGuiId(rs.getString("nguoiguiid"));
                message.setNguoiGuiTen(rs.getString("nguoiguiten"));
                message.setNguoiNhanId(rs.getString("nguoinhanid"));
                message.setNoiDung(rs.getString("noidung"));
                message.setGioGui(rs.getString("giogui"));
                message.setNgayGui(rs.getString("ngaygui"));
                message.setDaXemBoi(rs.getString("daxemboi"));
                list.add(message);
            }
            pr.close();
            rs.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<InternalChatMessage> getUserMessages(String fromUserId, String toUserId) {
        ArrayList<InternalChatMessage> list = new ArrayList<InternalChatMessage>();
        try {
            String query;
            if (toUserId.contains("Group")) {
                query = "SELECT * FROM nhantin WHERE nguoinhanid = ? ORDER BY matin ASC";
            } else {
                query = "SELECT * FROM nhantin WHERE (nguoinhanid = ? AND nguoiguiid = ?) OR (nguoiguiid = ? AND nguoinhanid = ?) ORDER BY matin ASC";
            }
            
            PreparedStatement pr = connection.prepareStatement(query);
            if (toUserId.contains("Group")) {
                pr.setString(1, toUserId);
            } else {
                pr.setString(1, toUserId);
                pr.setString(2, fromUserId);
                pr.setString(3, toUserId);
                pr.setString(4, fromUserId);
            }
            
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                InternalChatMessage message = new InternalChatMessage();
                message.setMaTin(rs.getInt("matin"));
                message.setNguoiGuiId(rs.getString("nguoiguiid"));
                message.setNguoiGuiTen(rs.getString("nguoiguiten"));
                message.setNguoiNhanId(rs.getString("nguoinhanid"));
                message.setNoiDung(rs.getString("noidung"));
                message.setGioGui(rs.getString("giogui"));
                message.setNgayGui(rs.getString("ngaygui"));
                message.setDaXemBoi(rs.getString("daxemboi"));
                list.add(message);
            }
            pr.close();
            rs.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    public NewMessageInfo getNewMessages(String fromUserId, String toUserId) {
        NewMessageInfo newMessage = new NewMessageInfo();
        String readByStr = "";
        
        try {
            String query;
            if (toUserId.contains("Group")) {
                query = "SELECT daxemboi, noidung, nguoiguiid, giogui, ngaygui FROM nhantin WHERE (nguoinhanid = ? AND nguoiguiid != ?) ORDER BY matin DESC";
            } else {
                query = "SELECT daxemboi, noidung, nguoiguiid, giogui, ngaygui FROM nhantin WHERE (nguoinhanid = ? AND nguoiguiid = ?) ORDER BY matin DESC";
            }
            
            PreparedStatement pr = connection.prepareStatement(query);
            if (toUserId.contains("Group")) {
                pr.setString(1, toUserId);
                pr.setString(2, fromUserId);
            } else {
                pr.setString(1, fromUserId);
                pr.setString(2, toUserId);
            }
            
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                readByStr = rs.getString("daxemboi");
                boolean isRead = isReadBy(readByStr, fromUserId);
                if (!isRead) {
                    newMessage.total++;
                    
                    if (toUserId.contains("Group")) {
                        newMessage.message = rs.getString("nguoiguiid") + " : " + rs.getString("noidung");
                    } else {
                        newMessage.message = rs.getString("noidung");
                    }
                    newMessage.messageTime = rs.getString("ngaygui");
                    if (newMessage.messageTime.equals(getCurrentDate())) {
                        newMessage.messageTime = rs.getString("giogui");
                    }
                }
            }
            pr.close();
            rs.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        
        if (newMessage.message == null) {
            newMessage = getLastMessage(fromUserId, toUserId);
        }
        return newMessage;
    }
    
    public NewMessageInfo getLastMessage(String fromUserId, String toUserId) {
        NewMessageInfo newMessage = new NewMessageInfo();
        newMessage.message = "Start new Conversation";
        
        try {
            String query;
            if (toUserId.contains("Group")) {
                query = "SELECT noidung, nguoiguiid, giogui, ngaygui FROM nhantin WHERE nguoinhanid = ? ORDER BY matin DESC LIMIT 1";
            } else {
                query = "SELECT noidung, nguoiguiid, giogui, ngaygui FROM nhantin WHERE (nguoinhanid = ? AND nguoiguiid = ?) OR (nguoiguiid = ? AND nguoinhanid = ?) ORDER BY matin DESC LIMIT 1";
            }
            
            PreparedStatement pr = connection.prepareStatement(query);
            if (toUserId.contains("Group")) {
                pr.setString(1, toUserId);
            } else {
                pr.setString(1, toUserId);
                pr.setString(2, fromUserId);
                pr.setString(3, toUserId);
                pr.setString(4, fromUserId);
            }
            
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                if (toUserId.contains("Group")) {
                    String from = fromUserId.equals(rs.getString("nguoiguiid")) ? "You" : rs.getString("nguoiguiid");
                    newMessage.message = from + " : " + rs.getString("noidung");
                } else {
                    newMessage.message = rs.getString("noidung");
                }
                newMessage.messageTime = rs.getString("ngaygui");
                if (newMessage.messageTime.equals(getCurrentDate())) {
                    newMessage.messageTime = rs.getString("giogui");
                }
            }
            pr.close();
            rs.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return newMessage;
    }
    
    public String getReadBy(int messageId) {
        try {
            String query = "SELECT daxemboi FROM nhantin WHERE matin = ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, messageId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String result = rs.getString("daxemboi");
                rs.close();
                st.close();
                return result;
            }
            rs.close();
            st.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }
    
    public boolean isReadBy(String readByString, String userId) {
        if (readByString == null || readByString.isEmpty()) {
            return false;
        }
        
        StringTokenizer readBy = new StringTokenizer(readByString, "#");
        while (readBy.hasMoreTokens()) {
            if (readBy.nextToken().equals(userId)) {
                return true;
            }
        }
        return false;
    }
    
    public void markAsRead(int messageId, String readBy) {
        try {
            String query = "UPDATE nhantin SET daxemboi = ? WHERE matin = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, readBy);
            pr.setInt(2, messageId);
            pr.executeUpdate();
            pr.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    
    public void addReadBy(ArrayList<Integer> messageIds, String userId) {
        try {
            StringBuilder query = new StringBuilder("UPDATE nhantin SET daxemboi = CONCAT(daxemboi, ?, '#') WHERE ");
            query.append("matin = ").append(messageIds.get(0));
            for (int i = 1; i < messageIds.size(); i++) {
                query.append(" OR matin = ").append(messageIds.get(i));
            }
            
            PreparedStatement pr = connection.prepareStatement(query.toString());
            pr.setString(1, userId);
            pr.executeUpdate();
            pr.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
    
    public int getNewMessageId() {
        try {
            String query = "SELECT COUNT(*) FROM nhantin";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int messageId = rs.getInt(1) + 1;
            rs.close();
            st.close();
            return messageId;
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return 0;
    }
    
    public int getUnreadMessageCount(String userId) {
        int total = 0;
        try {
            String query = "SELECT daxemboi FROM nhantin WHERE nguoinhanid = ?";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, userId);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                String readByStr = rs.getString("daxemboi");
                boolean isRead = isReadBy(readByStr, userId);
                if (!isRead) {
                    total++;
                }
            }
            rs.close();
            st.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return total;
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
}
