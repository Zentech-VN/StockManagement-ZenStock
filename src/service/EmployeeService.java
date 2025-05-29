package service;

import dao.EmployeeDAO;
import entity.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService implements EmployeeDAO {
    
    private List<Employee> employeeList = new ArrayList<>();
    
    public List<Employee> getAllEmployeeService() {
        return employeeList = getAllEmployee();
    }
    
    
    
}
