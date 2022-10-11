package com.backend.sartbux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@NoArgsConstructor
public class CartItem {
    String id;
    Product drink;
    Product topping;
    int quantity;
    private BigDecimal itemAmount;

    //TODO How to check if the topping is not null , Can we use Optional as field?
    public BigDecimal getItemAmount() {
        itemAmount = drink.getPrice().multiply(topping.getPrice()).multiply(BigDecimal.valueOf(quantity));
        return itemAmount;
    }
}
