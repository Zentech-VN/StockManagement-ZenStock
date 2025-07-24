package service;

import dao.ChartDAO;
import entity.Chart_Revenue;
import java.util.ArrayList;
import java.util.List;

public class ChartService implements ChartDAO{
    private List<Chart_Revenue> list = new ArrayList<>();

    public List<Chart_Revenue> getRevenue10MonthService() {
        return list = getRevenue10Month();
    }    
    
    public List<Chart_Revenue> getRevenueService() {
        return list = getRevenue();
    } 
}
