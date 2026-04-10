package org.guerrer0jaguar.inventory.merger.integration.provider.b;


import org.guerrer0jaguar.inventory.merger.integration.ProductBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductB extends ProductBase {
    private Double rating;  
}