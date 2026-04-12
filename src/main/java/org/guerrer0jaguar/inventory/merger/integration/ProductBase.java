package org.guerrer0jaguar.inventory.merger.integration;

import lombok.Data;

@Data
public class ProductBase {
      
    protected Long id;    
    protected String title;
    protected Double price;    
    protected String description;
    protected String category;
}