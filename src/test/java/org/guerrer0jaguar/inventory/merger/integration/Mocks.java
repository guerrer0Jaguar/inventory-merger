package org.guerrer0jaguar.inventory.merger.integration;

import java.util.ArrayList;
import java.util.List;

import org.guerrer0jaguar.inventory.merger.integration.provider.a.EndpointA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductAResponseWrapper;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.Rating;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.EndpointB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductBResponseWrapper;

final class Mocks {

    static final double RATING_A = 9.99;
    static final double RATING_B = 15.6;
    static final int DEFAULT_STOCK_FROM_A = 0;
    static final Long STOCK_FROM_B = 100L;

    static EndpointA createMockProviderA() {
        EndpointA providerA = new EndpointA() {
            @Override
            public ProductAResponseWrapper getProducts(String userAgent) {
                ProductA pr = new ProductA();
                pr.setId(1L);
                pr.setTitle("Product from source A");
                Rating rating = new Rating();
                rating.setRate("");
                rating.setCount(Double.valueOf(RATING_A));
                pr.setRating(rating);
                List<ProductA> products = new ArrayList<>();
                products.add(pr);

                ProductAResponseWrapper wrapper = new ProductAResponseWrapper();
                wrapper.setProducts(products);
                
                return wrapper;
            }
        };
        return providerA;
    }

    static EndpointB createMockProviderB() {
        EndpointB providerB = new EndpointB() {

            @Override
            public ProductBResponseWrapper getProducts(String userAgent) {
                ProductB pr = new ProductB();
                pr.setId(21L);
                pr.setTitle("Product from source B");
                pr.setRating(RATING_B);
                pr.setStock(STOCK_FROM_B);
                List<ProductB> products = new ArrayList<>();
                products.add(pr);

                ProductBResponseWrapper wrapper = new ProductBResponseWrapper();
                wrapper.setProducts(products);
                
                return wrapper;
            }
        };
        return providerB;
    }
}
