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
import org.guerrer0jaguar.inventory.merger.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private InventoryIntegrator integrator;

    @Value("classpath:products-for-product-test.json")
    private Resource productsFile;
        
    @Test
    void testSyncronizeProducts() throws IOException {
        List<Product> mockList = extractMockData();
        when(integrator.getProductsMerged()).thenReturn(mockList);

        List<Product> products = service.syncronizeProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        
        List<Product> anotherSave = service.syncronizeProducts();
        assertNotNull(anotherSave);
        assertTrue(anotherSave.isEmpty());
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