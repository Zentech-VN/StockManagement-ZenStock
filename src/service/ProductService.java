package service;

import dao.ProductDAO;

public class ProductService implements ProductDAO {
    public int getProductCountService() {
        return getProductCount();
    }
}
