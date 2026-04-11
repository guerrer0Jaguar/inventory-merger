package org.guerrer0jaguar.inventory.merger.integration.provider.b;


import java.util.List;

import org.guerrer0jaguar.inventory.merger.integration.Dimensions;
import org.guerrer0jaguar.inventory.merger.integration.Meta;
import org.guerrer0jaguar.inventory.merger.integration.ProductBase;
import org.guerrer0jaguar.inventory.merger.integration.Reviews;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductB extends ProductBase {
    
    private Double rating;  
    protected Long stock = 0L;
    
    private List<String> tags;

    private String brand;

    private String sku;

    private Integer weight;

    
    private Dimensions dimensions;

    private String warrantyInformation;

    private String shippingInformation;

    private String availabilityStatus;

    
    private List<Reviews> reviews;

    private String returnPolicy;

    private Integer minimumOrderQuantity;

    
    private Meta meta;
    
    private List<String> images;

    private String thumbnail;
    
    private Double discountPercentage;
    
    private String description;

    private String category;

}