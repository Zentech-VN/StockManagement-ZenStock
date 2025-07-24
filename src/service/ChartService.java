package service;

import dao.ChartDAO;
import entity.Chart_Revenue;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import raven.toast.Notifications;

public class ChartService implements ChartDAO {

    private List<Chart_Revenue> list = new ArrayList<>();
    private final int CURRENT_YEAR = Year.now().getValue();

    private final String YEAR_REGEX = "^[0-9]{4}$";
    private final String MONTH_REGEX = "^(0?[1-9]|1[0-2])$";

    public List<Chart_Revenue> getRevenue10MonthService() {
        return list = getRevenue10Month();
    }

    public List<Chart_Revenue> getRevenueService() {
        return list = getRevenue();
    }

    public List<Chart_Revenue> getRevenueYearsService(String fromYearText, String toYearText) {

        int fromYear = Integer.parseInt(fromYearText.trim());
        int toYear = Integer.parseInt(toYearText.trim());

        return getRevenueYears(fromYear, toYear);
    }

    public boolean getValidateRevenueYearsService(String fromYearText, String toYearText) {
        if (fromYearText.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được để trống Năm bắt đầu");
            return false;
        }

        if (toYearText.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được để trống Năm kết thúc");
            return false;
        }

        if (!fromYearText.matches(YEAR_REGEX) || !toYearText.matches(YEAR_REGEX)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Năm phải là số có 4 chữ số");
            return false;
        }

        int fromYear = Integer.parseInt(fromYearText.trim());
        int toYear = Integer.parseInt(toYearText.trim());

        if (fromYear <= 2000 || toYear <= 2000 || fromYear > CURRENT_YEAR || toYear > CURRENT_YEAR) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Năm nằm ngoài phạm vi cho phép (2000-" + CURRENT_YEAR + ")");
            return false;
        }

        if (fromYear > toYear) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Năm bắt đầu không được lớn hơn năm kết thúc");
            return false;
        }

        return true;
    }

    public List<Chart_Revenue> getRevenueMonthsService(String yearText) {

        int year = Integer.parseInt(yearText.trim());

        return getRevenueMonths(year);
    }

    public boolean getValidateRevenueMonthsService(String yearText) {
        if (yearText.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được để trống Năm");
            return false;
        }

        if (!yearText.matches(YEAR_REGEX)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Năm phải là số có 4 chữ số");
            return false;
        }

        int year = Integer.parseInt(yearText.trim());

        if (year <= 2000 || year > CURRENT_YEAR) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Năm không hợp lệ (2000-" + CURRENT_YEAR + ")");
            return false;
        }

        return true;
    }

    public List<Chart_Revenue> getRevenueDaysService(String yearText, String monthText) {

        int year = Integer.parseInt(yearText.trim());
        int month = Integer.parseInt(monthText.trim());

        return getRevenueDays(year, month);
    }

    public boolean getValidateRevenueDaysService(String yearText, String monthText) {
        if (yearText.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được để trống Năm");
            return false;
        }

        if (monthText.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Không được để trống Tháng");
            return false;
        }

        if (!yearText.matches(YEAR_REGEX)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Năm phải là số có 4 chữ số");
            return false;
        }

        if (!monthText.matches(MONTH_REGEX)) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tháng phải từ 1 đến 12");
            return false;
        }

        int year = Integer.parseInt(yearText.trim());
        int month = Integer.parseInt(monthText.trim());

        if (year <= 2000 || year > CURRENT_YEAR) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Năm không hợp lệ (2000-" + CURRENT_YEAR + ")");
            return false;
        }

        return true;
    }

}
