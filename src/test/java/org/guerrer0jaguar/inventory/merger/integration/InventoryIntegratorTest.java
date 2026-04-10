package org.guerrer0jaguar.inventory.merger.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.EndpointA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.Rating;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.EndpointB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductB;
import org.junit.jupiter.api.Test;


class InventoryIntegratorTest {

    private static final double RATING_A = 9.99;

    @Test
    void getProducts() {
        
        EndpointA providerA = new EndpointA() {           
            @Override
            public List<ProductA> getProducts() {         
                ProductA pr = new ProductA();
                pr.setId(1L);
                pr.setTitle("Product1");
                Rating rating = new Rating();
                rating.setRate("");
                rating.setCount(Double.valueOf(RATING_A));
                pr.setRating(rating);
                List<ProductA> products = new ArrayList<>();
                products.add(pr);
                return products;
            }
        };
        
        EndpointB providerB = new EndpointB() {
            
            @Override
            public List<ProductB> getProducts() {
                return new ArrayList<>();
            }
        };
        
        InventoryIntegrator integrator = new InventoryIntegratorImpl(providerA, providerB);
        List<Product> productsMerged = integrator.getProductsMerged();
        assertNotNull(productsMerged);
        assertFalse(productsMerged.isEmpty());
        validateProductA(productsMerged);
    }

    private void validateProductA(
            List<Product> productsMerged) {
        Product firstProduct = productsMerged.get(0);
        assertTrue(firstProduct.getId().equals(1L));
        assertTrue(firstProduct.getRating().equals(RATING_A));
        assertTrue(firstProduct.getProvider().equals(ProviderSource.A));
    }
}