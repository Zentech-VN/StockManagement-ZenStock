package service;

import dao.BrandDAO;
import entity.Brand;
import java.util.ArrayList;
import java.util.List;

public class BrandService implements BrandDAO{
    private List<Brand> brand = new ArrayList<>();

    public List<Brand> getAllBrandsService() {
        return brand = getAllBrands();
    }
}
