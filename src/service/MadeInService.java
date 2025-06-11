package service;

import dao.MadeInDAO;
import entity.MadeIn;
import java.util.ArrayList;
import java.util.List;

public class MadeInService implements MadeInDAO {
    private List<MadeIn> madeIn = new ArrayList<>();

    public List<MadeIn> getAllMadeInService() {
        return madeIn = getAllMadeIn();
    }  
}
