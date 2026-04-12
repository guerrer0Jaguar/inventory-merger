package org.guerrer0jaguar.inventory.merger.canonic;

import lombok.Data;

@Data
public class ProductFilter {

    private Double minRating;
    private Double maxPrice;
    private Integer minStock;
    private ProviderSource provider;
}