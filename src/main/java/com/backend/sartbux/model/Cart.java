package com.backend.sartbux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    String id;
    List<CartItem> cartItems;
    private BigDecimal cartAmount;

    public BigDecimal getCartAmount() {
        return cartItems.stream().map(CartItem::getItemAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
