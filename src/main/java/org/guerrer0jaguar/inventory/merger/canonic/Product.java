package org.guerrer0jaguar.inventory.merger.canonic;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Product extends ProductBase {
   
    private ProviderSource provider;
    private Double rating;
    
}