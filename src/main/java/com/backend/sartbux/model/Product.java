package com.backend.sartbux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Product {
    String id;
    String name;
    BigDecimal price;
    ProductType productType;

    public Product(ProductType productType) {
        this.productType = productType;
    }
}


;
