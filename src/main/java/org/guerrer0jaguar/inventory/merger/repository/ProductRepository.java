package org.guerrer0jaguar.inventory.merger.repository;

import java.util.Optional;

import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Modifying
    @Query("UPDATE Product p SET p.stock = :newStock WHERE p.stock = :stockToFind")
    int updateStock(
            @Param("newStock") Long newStock,
            @Param("stockToFind") Long stockToFind);
    
    Optional<Product> findByExternalIdAndProvider(Long externalId, ProviderSource provider);
}
