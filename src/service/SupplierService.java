package service;

import dao.SupplierDAO;
import entity.Supplier;
import java.util.List;

public class SupplierService implements SupplierDAO {

    public List<Supplier> getAllSuppliers() {
        return getAll();
    }

    public boolean deleteSupplier(int maNhaCungCap) {
        return delete(maNhaCungCap);
    }

    public boolean insertSupplier(Supplier supplier) {
        return insert(supplier);
    }

    public boolean updateSupplier(Supplier supplier) {
        return update(supplier);
    }

    public boolean updateTrangThai(int maNCC, int trangThai) {
        return updateTrangThai(maNCC, trangThai);
    }

}
