package service;

import dao.OSDAO;
import entity.OS;
import java.util.ArrayList;
import java.util.List;

public class OSService implements OSDAO{
    private List<OS> os = new ArrayList<>();

    public List<OS> getAllOSService() {
        return os = getAllOS();
    }    
}
