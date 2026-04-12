package org.guerrer0jaguar.inventory.merger.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Disabled("it tests the real connection to the providers!! ")
class InventoryIntegrator_ConnectionTest {

    @Autowired
    private InventoryIntegrator integrator;
        
    @Test
    void testConnection() {
        assertNotNull(integrator);
        List<Product> products = integrator.getProductsMerged();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }
}
