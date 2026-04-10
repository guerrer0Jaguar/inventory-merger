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
    private static final double RATING_B = 15.6;
    private static final int DEFAULT_STOCK_FROM_A = 0;
    private static final Long STOCK_FROM_B = 100L;

    @Test
    void getProducts() {

        EndpointA providerA = new EndpointA() {
            @Override
            public List<ProductA> getProducts() {
                ProductA pr = new ProductA();
                pr.setId(1L);
                pr.setTitle("Product from source A");
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
                ProductB pr = new ProductB();
                pr.setId(21L);
                pr.setTitle("Product from source B");
                pr.setRating(RATING_B);
                pr.setStock(STOCK_FROM_B);
                List<ProductB> products = new ArrayList<>();
                products.add(pr);

                return products;
            }
        };

        InventoryIntegrator integrator = new InventoryIntegratorImpl(providerA,
                providerB);
        List<Product> productsMerged = integrator.getProductsMerged();
        assertNotNull(productsMerged);
        assertFalse(productsMerged.isEmpty());
        validateProductA(productsMerged);
        validateProductB(productsMerged);
    }

    private void validateProductA(
            List<Product> productsMerged) {
        Product firstProduct = productsMerged.get(0);
        assertTrue(firstProduct.getId().equals(1L));
        assertTrue(firstProduct.getRating().equals(RATING_A));
        assertTrue(firstProduct.getStock().longValue() == DEFAULT_STOCK_FROM_A);
        assertTrue(firstProduct.getProvider().equals(ProviderSource.A));
    }

    private void validateProductB(
            List<Product> productsMerged) {
        Product secondProduct = productsMerged.get(1);
        assertTrue(secondProduct.getId().equals(21L));
        assertTrue(secondProduct.getRating().equals(RATING_B));
        assertTrue(secondProduct.getStock().longValue() == STOCK_FROM_B);
        assertTrue(secondProduct.getProvider().equals(ProviderSource.B));
    }

}