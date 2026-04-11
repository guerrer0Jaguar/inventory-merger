package org.guerrer0jaguar.inventory.merger.repository;

import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
