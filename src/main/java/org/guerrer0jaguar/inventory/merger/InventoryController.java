package org.guerrer0jaguar.inventory.merger;

import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    
    private final ProductService service;
    
    public InventoryController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/inventory")
    public List<Product> findAll(){
        return service.syncronizeProducts();
    }
}
