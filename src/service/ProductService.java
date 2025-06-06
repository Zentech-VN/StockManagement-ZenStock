package service;

import dao.ProductDAO;
import entity.Employee;
import entity.ProductView;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements ProductDAO {
    private List<ProductView> productViewList = new ArrayList<>();
    
    public List<ProductView> getAllProductViewService() {
        return productViewList = getAllProduct();
    }
    
    public int getProductCountService() {
        return getProductCount();
    }

    
    
}
