package org.guerrer0jaguar.inventory.merger.canonic;

import java.util.List;

import lombok.Data;

@Data
public class ProductBase {
    
    protected Long id;

    protected String title;

    protected String description;

    protected String category;

    protected Double price;

    protected Double discountPercentage;

    protected Long stock = 0L;

    protected List<String> tags;

    protected String brand;

    protected String sku;

    protected Integer weight;

    protected Dimensions dimensions;

    protected String warrantyInformation;

    protected String shippingInformation;

    protected String availabilityStatus;

    protected List<Reviews> reviews;

    protected String returnPolicy;

    protected Integer minimumOrderQuantity;

    protected Meta meta;

    protected List<String> images;

    protected String thumbnail;

}