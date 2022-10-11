package com.backend.sartbux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    String id;
    Cart cart;
    Promotion promotion;
    BigDecimal total;
}
