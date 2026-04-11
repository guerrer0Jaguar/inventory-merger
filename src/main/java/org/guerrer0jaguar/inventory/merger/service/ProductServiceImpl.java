package org.guerrer0jaguar.inventory.merger.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.guerrer0jaguar.inventory.merger.ReestockRequest;
import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
       
       List<Product> productsFiltered = productsMerged
               .stream()
               .filter(this::isNotSaved)
               .collect(Collectors.toList());
                 
       return productsFiltered
           .stream()
           .map(pr -> repository.save(pr))
           .collect(Collectors.toList());                          
    }

    private boolean isNotSaved(Product toFind) {        
        Long externalId = toFind.getExternalId();
        ProviderSource provider = toFind.getProvider();
        Optional<Product> productFound = repository.findByExternalIdAndProvider(externalId, provider);
        
        return ! productFound.isPresent();
    }

    @Override
    @Transactional
    public void reestock(
            ReestockRequest request) {
        long updated =
                repository.updateStock(request.getStock(), request.getStockToFind());
        
        log.info("Reestock operation, products updated: {}", updated);
    }
}