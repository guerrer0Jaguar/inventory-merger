package org.guerrer0jaguar.inventory.merger.integration;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.guerrer0jaguar.inventory.merger.canonic.InventoryIntegrator;
import org.guerrer0jaguar.inventory.merger.canonic.Product;
import org.guerrer0jaguar.inventory.merger.canonic.ProviderSource;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.EndpointA;
import org.guerrer0jaguar.inventory.merger.integration.provider.a.ProductA;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.EndpointB;
import org.guerrer0jaguar.inventory.merger.integration.provider.b.ProductB;
import org.springframework.beans.BeanUtils;

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
        
        
        List<Product> fullList = new ArrayList<>(canonizeListA(firstList));
        fullList.addAll(canonizeListB(secondList));
        
        return fullList;
    }


    private List<Product> canonizeListA(
            List<ProductA> firstList) {
       
        return firstList
            .stream()
            .map(this::canonizeProductA)
            .collect(Collectors.toList());
        
    }

    private Product canonizeProductA(
            ProductA productA) {
        Product canonized = new Product();
        BeanUtils.copyProperties(productA, canonized);
        canonized.setRating(productA.getRating().getCount());
        canonized.setProvider(ProviderSource.A);
        return canonized;                
    } 
    
    private List<Product> canonizeListB(
            List<ProductB> secondList) {
        
        return secondList
                .stream()
                .map(this::canonizeProductB)
                .collect(Collectors.toList());
    }

    private Product canonizeProductB(
            ProductB productB) {
        
        Product canonized = new Product();
        BeanUtils.copyProperties(productB, canonized);
        canonized.setProvider(ProviderSource.B);
        return canonized;
    }
    
    

}
