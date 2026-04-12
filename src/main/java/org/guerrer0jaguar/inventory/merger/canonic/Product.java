package org.guerrer0jaguar.inventory.merger.canonic;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class Product {   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long canonicId;
    @Column(length = 1024)
    private String title;
    protected Double price;
    @Column(length = 1024)
    private String description;
    protected String category;
    
    private Long externalId;    
    private ProviderSource provider;
    private Double rating;      
    protected Long stock = 0L;
    
    @Override
    public int hashCode() {
        return Objects.hash(canonicId);
    }
    
    @Override
    public boolean equals(
            Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return Objects.equals(canonicId, other.canonicId);
    }        
       
}