package org.guerrer0jaguar.inventory.merger.canonic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long externalId;
    
    private ProviderSource provider;
    private Double rating;  
    
    protected Long stock = 0L;
    private String description;
    private String title;
    protected Double price;    
}