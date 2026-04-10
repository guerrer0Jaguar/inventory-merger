package org.guerrer0jaguar.inventory.merger.canonic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
public class Product extends ProductBase {   
    private ProviderSource provider;
    private Double rating;  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long internalId;
}