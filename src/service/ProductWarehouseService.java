package service;

import dao.ProductWarehouseDAO;
import entity.ProductWarehouse;
import java.util.ArrayList;
import java.util.List;

public class ProductWarehouseService implements ProductWarehouseDAO {
    private List<ProductWarehouse> productWarehouses = new ArrayList<>();

    public List<ProductWarehouse> getAllProductWarehouseService() {
        return productWarehouses = getAllProductWarehouse();
    }  
}
