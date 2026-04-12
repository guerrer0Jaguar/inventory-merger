package org.guerrer0jaguar.inventory.merger.service;

import java.util.List;

import org.guerrer0jaguar.inventory.merger.ReestockRequest;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProductFilter;

public interface ProductService {

    public List<Product> syncronizeProducts();

    public long reestock(
            ReestockRequest request);
    
    public List<Product> findProducts(ProductFilter filter);
}
