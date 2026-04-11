package org.guerrer0jaguar.inventory.merger;

import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.Product;

public interface ProductService {

    public List<Product> syncronizeProducts();

    public void reestock(
            ReestockRequest request);
}
