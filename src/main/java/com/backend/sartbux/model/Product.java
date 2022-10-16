package com.backend.sartbux.model;

import com.backend.sartbux.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    String id;
    String name;
    BigDecimal price;
    ProductType productType;
}
