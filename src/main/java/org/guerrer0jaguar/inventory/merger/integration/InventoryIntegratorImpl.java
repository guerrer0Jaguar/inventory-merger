package org.guerrer0jaguar.inventory.merger.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.EndpointA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductAResponseWrapper;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.EndpointB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductBResponseWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InventoryIntegratorImpl implements InventoryIntegrator {
    
    @Value("${feign.client.config.default.user-agent}")    
    private String userAgent;

    private final EndpointA providerA;
    private final EndpointB providerB;

    public InventoryIntegratorImpl(EndpointA providerA, EndpointB providerB) {
        this.providerA = providerA;
        this.providerB = providerB;
    }

    @Override
    public List<Product> getProductsMerged() {
        List<ProductA> firstList = fetchProductsFromProviderA();
        List<ProductB> secondList = fetchProductsFromProviderB();

        List<Product> fullList = new ArrayList<>(canonizeListA(firstList));
        fullList.addAll(canonizeListB(secondList));

        return fullList;
    }

    private List<ProductA> fetchProductsFromProviderA() {
        
        try {
            ProductAResponseWrapper wrapper = providerA.getProducts(userAgent);
            return wrapper.getProducts();
        } catch (FeignException e) {            
            log.error("An error occurred when fetching products from provider A: ", e);
            return new ArrayList<>();
        }
    }
    
    private List<ProductB> fetchProductsFromProviderB() {
        try {
            ProductBResponseWrapper wrapper = providerB.getProducts(userAgent);
            return wrapper.getProducts();
        } catch (FeignException e) {
            log.error("An error occurred when fetching products from provider B: ", e);
            return new ArrayList<>();
        }
    }

    private List<Product> canonizeListA(
            List<ProductA> firstList) {

        return firstList
                .stream()
                .map(this::canonizeProductA)
                .collect(Collectors.toList());

    }

    private Product canonizeProductA(
            ProductA productA) {
        Product canonized = new Product();
        BeanUtils.copyProperties(productA, canonized);
        canonized.setRating(productA.getRating().getCount());
        canonized.setProvider(ProviderSource.ProviderA);
        canonized.setExternalId(productA.getId());
        return canonized;
    }

    private List<Product> canonizeListB(
            List<ProductB> secondList) {

        return secondList
                .stream()
                .map(this::canonizeProductB)
                .collect(Collectors.toList());
    }

    private Product canonizeProductB(
            ProductB productB) {

        Product canonized = new Product();
        BeanUtils.copyProperties(productB, canonized);
        canonized.setProvider(ProviderSource.ProviderB);
        canonized.setExternalId(productB.getId());
        return canonized;
    }
}