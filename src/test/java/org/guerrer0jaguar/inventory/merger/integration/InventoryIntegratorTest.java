package org.guerrer0jaguar.inventory.merger.integration;

import static org.guerrer0jaguar.inventory.merger.integration.Mocks.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.EndpointA;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.EndpointB;
import org.junit.jupiter.api.Test;

class InventoryIntegratorTest {

    @Test
    void getProducts() {

        EndpointA providerA = Mocks.createMockProviderA();
        EndpointB providerB = Mocks.createMockProviderB();

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
        assertTrue(firstProduct.getProvider().equals(ProviderSource.ProviderA));
    }

    private void validateProductB(
            List<Product> productsMerged) {
        Product secondProduct = productsMerged.get(1);
        assertTrue(secondProduct.getId().equals(21L));
        assertTrue(secondProduct.getRating().equals(RATING_B));
        assertTrue(secondProduct.getStock().longValue() == STOCK_FROM_B);
        assertTrue(secondProduct.getProvider().equals(ProviderSource.ProviderB));
    }
}