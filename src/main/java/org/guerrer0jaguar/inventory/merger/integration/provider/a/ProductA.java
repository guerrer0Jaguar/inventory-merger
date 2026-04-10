package org.guerrer0jaguar.inventory.merger.integration.provider.a;

import org.guerrer0jaguar.inventory.merger.canonic.ProductBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductA extends ProductBase {
    private Rating rating;
}