package entity;

import java.sql.Date;

public class DestructionReleaseNote {

    private int id;
    private Date date;
    private String status;
    private String creator;
    private String lyDo;
    private String malmel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getMalmel() {
        return malmel;
    }

    public void setMalmel(String malmel) {
        this.malmel = malmel;
    }
    
    

    public DestructionReleaseNote(int id, Date date, String status, String creator, String lyDo, String malmel) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.creator = creator;
        this.lyDo = lyDo;
        this.malmel = malmel;
    }

    public DestructionReleaseNote() {
    }

}
