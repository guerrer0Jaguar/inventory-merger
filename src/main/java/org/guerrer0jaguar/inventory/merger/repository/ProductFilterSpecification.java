package org.guerrer0jaguar.inventory.merger.repository;

import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.springframework.data.jpa.domain.Specification;

public class ProductFilterSpecification {

    public static Specification<Product> idIsValid() {
        return (
                root,
                query,
                criteria) -> criteria.isNotNull(root.get("canonicId"));
    }

    public static Specification<Product> ratingIsGreaterThan(
            Double minRating) {
        return (
                root,
                query,
                criteria) -> criteria
                        .greaterThanOrEqualTo(root.get("rating"), minRating);
    }

    public static Specification<Product> priceIsLessThanOrEqualTo(
            Double maxPrice) {
        return (
                root,
                query,
                criteria) -> criteria
                        .lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> stockIsGreatherThan(
            Integer minStock) {
        return (
                root,
                query,
                criteria) -> criteria
                        .greaterThanOrEqualTo(root.get("stock"), minStock);
    }

    public static Specification<Product> providerIsEqualTo(
            ProviderSource provider) {
        return (
                root,
                query,
                criteria) -> criteria.equal(root.get("provider"), provider);
    }

}
