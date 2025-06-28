package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InternalChatMessage {
    
    private int maTin;         
    private String nguoiGuiId;    
    private String nguoiGuiTen;  
    private String nguoiNhanId; 
    private String noiDung;      
    private String gioGui;    
    private String ngayGui;   
    private String daXemBoi;     
    
    public InternalChatMessage() {
        Date now = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aaa");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM,yyyy");
        
        this.gioGui = timeFormat.format(now);
        this.ngayGui = dateFormat.format(now);
        this.daXemBoi = "";
    }
    
    public InternalChatMessage(String nguoiGuiId, String nguoiGuiTen, String nguoiNhanId, String noiDung) {
        this();
        this.nguoiGuiId = nguoiGuiId;
        this.nguoiGuiTen = nguoiGuiTen;
        this.nguoiNhanId = nguoiNhanId;
        this.noiDung = noiDung;
    }
    
    public int getMaTin() {
        return maTin;
    }
    
    public void setMaTin(int maTin) {
        this.maTin = maTin;
    }
    
    public String getNguoiGuiId() {
        return nguoiGuiId;
    }
    
    public void setNguoiGuiId(String nguoiGuiId) {
        this.nguoiGuiId = nguoiGuiId;
    }
    
    public String getNguoiGuiTen() {
        return nguoiGuiTen;
    }
    
    public void setNguoiGuiTen(String nguoiGuiTen) {
        this.nguoiGuiTen = nguoiGuiTen;
    }
    
    public String getNguoiNhanId() {
        return nguoiNhanId;
    }
    
    public void setNguoiNhanId(String nguoiNhanId) {
        this.nguoiNhanId = nguoiNhanId;
    }
    
    public String getNoiDung() {
        return noiDung;
    }
    
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
    
    public String getGioGui() {
        return gioGui;
    }
    
    public void setGioGui(String gioGui) {
        this.gioGui = gioGui;
    }
    
    public String getNgayGui() {
        return ngayGui;
    }
    
    public void setNgayGui(String ngayGui) {
        this.ngayGui = ngayGui;
    }
    
    public String getDaXemBoi() {
        return daXemBoi;
    }
    
    public void setDaXemBoi(String daXemBoi) {
        this.daXemBoi = daXemBoi;
    }
    
    public boolean isReadByUser(String userId) {
        if (daXemBoi == null || daXemBoi.isEmpty()) {
            return false;
        }
        return daXemBoi.contains(userId);
    }
   
    public void markAsReadBy(String userId) {
        if (daXemBoi == null) {
            daXemBoi = "";
        }
        
        if (!isReadByUser(userId)) {
            if (daXemBoi.isEmpty()) {
                daXemBoi = userId;
            } else {
                daXemBoi += "#" + userId;
            }
        }
    }
    
    public boolean isGroupMessage() {
        return nguoiNhanId != null && nguoiNhanId.contains("Group");
    }
    
    public String getDisplayTime() {
        String currentDate = getCurrentDate();
        if (ngayGui != null && ngayGui.equals(currentDate)) {
            return gioGui;
        }
        return ngayGui;
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
    
    public boolean isFromToday() {
        return ngayGui != null && ngayGui.equals(getCurrentDate());
    }

    public String getPreview(int maxLength) {
        if (noiDung == null) {
            return "";
        }
        
        if (noiDung.length() <= maxLength) {
            return noiDung;
        }
        
        return noiDung.substring(0, maxLength - 3) + "...";
    }
    
    public String getSenderDisplayName() {
        if (nguoiGuiTen != null && !nguoiGuiTen.trim().isEmpty()) {
            return nguoiGuiTen;
        }
        return nguoiGuiId;
    }
    
    public boolean isSentBy(String userId) {
        return nguoiGuiId != null && nguoiGuiId.equals(userId);
    }
    
    public boolean isSentTo(String userId) {
        return nguoiNhanId != null && nguoiNhanId.equals(userId);
    }
    
    @Override
    public String toString() {
        return "InternalChatMessage{" +
                "maTin=" + maTin +
                ", nguoiGuiId='" + nguoiGuiId + '\'' +
                ", nguoiGuiTen='" + nguoiGuiTen + '\'' +
                ", nguoiNhanId='" + nguoiNhanId + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", gioGui='" + gioGui + '\'' +
                ", ngayGui='" + ngayGui + '\'' +
                ", daXemBoi='" + daXemBoi + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        InternalChatMessage that = (InternalChatMessage) obj;
        return maTin == that.maTin;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(maTin);
    }
}
