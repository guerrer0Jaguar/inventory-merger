package org.guerrer0jaguar.inventory.merger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceTest {
    
    @Autowired
    private ProductService service;

    @Test
    void testSyncronizeProducts() {
        List<Product> products = service.syncronizeProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

}
