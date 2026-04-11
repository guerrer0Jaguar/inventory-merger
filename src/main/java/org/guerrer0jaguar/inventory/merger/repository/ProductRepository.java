package org.guerrer0jaguar.inventory.merger.repository;

import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.stock = :newStock WHERE p.stock = :stockToFind")
    int updateStock(
            @Param("newStock") Long newStock,
            @Param("stockToFind") Long stockToFind);
}
