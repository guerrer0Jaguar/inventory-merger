package org.guerrer0jaguar.inventory.merger;

import java.util.List;
import java.util.stream.Collectors;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository repository;
    private final InventoryIntegrator integrator;    

    public ProductServiceImpl(ProductRepository repository,
            InventoryIntegrator integrator) {        
        this.repository = repository;
        this.integrator = integrator;
    }

    @Override
    public List<Product> syncronizeProducts() {
       List<Product> productsMerged = integrator.getProductsMerged();        
       
       return 
       productsMerged
           .stream()
           .map(pr -> repository.save(pr))
           .collect(Collectors.toList());                          
    }
}
