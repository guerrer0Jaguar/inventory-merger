package org.guerrer0jaguar.inventory.merger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProductFilter;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ProductService_findProductsTest {

    private static final Double MIN_RATING = Double.valueOf(10.1);
    private static final int EXPECTED_PRODUCTS_BY_RATING = 2;
    
    private static final Double MAX_PRICE = Double.valueOf(100.1);
    private static final int EXPECTED_PRODUCTS_BY_PRICE = 19;
    
    private static final Integer MIN_STOCK = Integer.valueOf(1000);
    private static final int EXPECTED_PRODUCTS_BY_STOCK = 2;
    
    private static final ProviderSource PROVIDER_TO_FIND = ProviderSource.ProviderA;
    private static final int EXPECTED_PRODUCTS_BY_PROVIDER = 10;

    @Autowired
    private ProductService service;

    @MockBean
    private InventoryIntegrator integrator;

    @Value("classpath:products-for-product-test.json")
    private Resource productsFile;
    
    private List<Product> mockList;
    
    @BeforeAll
    void init() throws IOException {
        mockList = extractMockData();
        when(integrator.getProductsMerged()).thenReturn(mockList);
        service.syncronizeProducts();
    }
           
    @Test
    void testFindProductsByRating() {       
        
        ProductFilter filter = new ProductFilter();
        filter.setMinRating(MIN_RATING);
        List<Product> products = service.findProducts(filter);        
        assertEquals(EXPECTED_PRODUCTS_BY_RATING, products.size());
    }
    
    @Test
    void testFindProductsByPrice() {
        
        ProductFilter filter = new ProductFilter();
        filter.setMaxPrice(MAX_PRICE);
        List<Product> products = service.findProducts(filter);
        assertEquals(EXPECTED_PRODUCTS_BY_PRICE, products.size());
    }
    
    @Test
    void testFindProductsByStock() {
        
        ProductFilter filter = new ProductFilter();
        filter.setMinStock(MIN_STOCK);
        List<Product> products = service.findProducts(filter);
        assertEquals(EXPECTED_PRODUCTS_BY_STOCK, products.size());
    }
    
    @Test
    void testFindProductsByProvider() {
        
        service.syncronizeProducts();
        ProductFilter filter = new ProductFilter();
        filter.setProvider(PROVIDER_TO_FIND);
        List<Product> products = service.findProducts(filter);
        assertEquals(EXPECTED_PRODUCTS_BY_PROVIDER, products.size());
    }

    private List<Product> extractMockData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = productsFile.getInputStream()) {
            String productsJSON = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            return mapper
                    .readValue(productsJSON,
                            new TypeReference<List<Product>>() {
                            });
        }
    }
}