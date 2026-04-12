package org.guerrer0jaguar.inventory.merger.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.guerrer0jaguar.inventory.merger.ReestockRequest;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProductFilter;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    
    private static final Long STOCK_TO_FIX = 0L;
    
    private final ProductService service;
    
    public InventoryController(ProductService service) {
        this.service = service;
    }
    
    @GetMapping("/inventory")
    public List<Product> find(
            @RequestParam( required = false) Double minRating,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minStock,
            @RequestParam(required = false) ProviderSource provider)
    {
        
        ProductFilter filter = new ProductFilter();
        
        if(! Objects.isNull(minRating)) {
            filter.setMinRating(minRating);
        }
        
        if(! Objects.isNull(maxPrice)) {
            filter.setMaxPrice(maxPrice);
        }
        
        if(! Objects.isNull(minStock)) {
            filter.setMinStock(minStock);
        }               
        
        if(! Objects.isNull(provider)) {
            filter.setProvider(provider);
        }
                
        return service.findProducts(filter);
    }

    @GetMapping("/syncronize")
    public List<Product> syncronize(){
        return service.syncronizeProducts();
    }
    
    @PatchMapping("/inventory/restock-zeros")
    public CustomHTTPmessage reestock(
            @RequestBody ReestockRequest request) {
        request.setStockToFind(STOCK_TO_FIX);
        long updated = service.reestock(request);
        String message = "Products updated: " + updated;

        return new CustomHTTPmessage(HttpStatus.OK.value(), message,
                LocalDateTime.now());

    }
}