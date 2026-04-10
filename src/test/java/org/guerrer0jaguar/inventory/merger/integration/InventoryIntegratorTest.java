package org.guerrer0jaguar.inventory.merger.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.EndpointA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductA;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.EndpointB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductB;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InventoryIntegratorTest {

    @Test
    void getProducts() {
        
        EndpointA providerA = new EndpointA() {           
            @Override
            public List<ProductA> getProducts() {               
                return new ArrayList<>();
            }
        };
        
        EndpointB providerB = new EndpointB() {
            
            @Override
            public List<ProductB> getProducts() {
                return new ArrayList<>();
            }
        };
        
        InventoryIntegrator integrator = new InventoryIntegratorImpl(providerA, providerB);
        assertNotNull(integrator.getProductsMerged());
    }
}