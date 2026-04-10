package org.guerrer0jaguar.inventory.merger.integration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.EndpointA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductA;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.EndpointB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductB;

public class InventoryIntegratorImpl  implements InventoryIntegrator {
    
    private final EndpointA providerA;
    private final EndpointB providerB;

    
   
    public InventoryIntegratorImpl(EndpointA providerA, EndpointB providerB) {        
        this.providerA = providerA;
        this.providerB = providerB;
    }



    @Override
    public List<Product> getProductsMerged() {
        List<ProductA> firstList = providerA.getProducts();
        List<ProductB> secondList = providerB.getProducts();
        
        
        List<Product> fullList = new ArrayList<>(canonizeList(firstList));
        
        
        return fullList;
    }



    private List<Product> canonizeList(
            List<ProductA> firstList) {
        List<Product> canonizedList = new ArrayList<>();
        firstList.stream().map(this::canonizeProductA);
        
        return new ArrayList<>();
    }

    private Product canonizeProductA(
            ProductA producta1) {
        
        return new Product();
    }

 
}
