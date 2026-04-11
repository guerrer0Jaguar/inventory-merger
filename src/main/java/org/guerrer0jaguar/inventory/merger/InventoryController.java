package org.guerrer0jaguar.inventory.merger;

import java.util.List;

import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    
    private static final Long STOCK_TO_FIX = 0L;
    
    private final ProductService service;
    
    public InventoryController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/syncronize")
    public List<Product> findAll(){
        return service.syncronizeProducts();
    }
    
    @PatchMapping("restock-zeros")
    public void reestock(@RequestBody ReestockRequest request) {
        request.setStockToFind(STOCK_TO_FIX);
        service.reestock(request);
    }
    
    
}