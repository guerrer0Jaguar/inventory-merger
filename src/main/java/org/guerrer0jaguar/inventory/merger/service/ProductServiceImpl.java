package org.guerrer0jaguar.inventory.merger.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.guerrer0jaguar.inventory.merger.ReestockRequest;
import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProductFilter;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.repository.ProductFilterSpecification;
import org.guerrer0jaguar.inventory.merger.repository.ProductRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Scheduled(fixedRateString = "${spring.task.scheduling.product-sync-fixed-rate}")
    @Transactional
    @Override    
    public List<Product> syncronizeProducts() {
        log.info("starting...");
        List<Product> productsMerged = integrator.getProductsMerged();
        log.info("fetched: {}", productsMerged.size());

        List<Product> productsFiltered = productsMerged
                .stream()
                .filter(this::isNotSaved)
                .collect(Collectors.toList());

        List<Product> productsSaved = 
                repository.saveAll(productsFiltered);
    
        log.info("saved: {}", productsSaved.size());

        return productsSaved;
    }

    boolean isNotSaved(
            Product toFind) {
        Long externalId = toFind.getExternalId();
        ProviderSource provider = toFind.getProvider();
        Optional<Product> productFound = repository
                .findByExternalIdAndProvider(externalId, provider);

        return !productFound.isPresent();
    }

    @Override
    @Transactional
    public void reestock(
            ReestockRequest request) {
        long updated = repository
                .updateStock(request.getStock(), request.getStockToFind());

        log.info("Reestock operation, products updated: {}", updated);
    }

    @Override
    public List<Product> findProducts(
            ProductFilter filter) {

        Specification<Product> spec = Specification
                .where(ProductFilterSpecification.idIsValid());

        if (!Objects.isNull(filter.getMinRating())) {
            spec = spec
                    .and(ProductFilterSpecification
                            .ratingIsGreaterThan(filter.getMinRating()));
        }

        if (!Objects.isNull(filter.getMaxPrice())) {
            spec = spec
                    .and(ProductFilterSpecification
                            .priceIsLessThanOrEqualTo(filter.getMaxPrice()));
        }

        if (!Objects.isNull(filter.getMinStock())) {
            spec = spec
                    .and(ProductFilterSpecification
                            .stockIsGreatherThan(filter.getMinStock()));
        }
        
        if (!Objects.isNull(filter.getProvider())) {
            spec = spec
                    .and(ProductFilterSpecification.providerIsEqualTo(filter.getProvider()));
        }

        return repository.findAll(spec);
    }
}