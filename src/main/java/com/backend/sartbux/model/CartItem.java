package com.backend.sartbux.model;

import com.backend.sartbux.exception.CartItemException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    String id;
    Product drink;
    List<Product> toppings;
    int quantity;

    public BigDecimal getItemAmount() throws CartItemException {
        if (drink != null)
            return drink.getPrice().add(getToppingAmount()).multiply(BigDecimal.valueOf(quantity));
        else
            throw new CartItemException("There is no drink in the cart item: "+ id);
    }

    public BigDecimal getToppingAmount() {
        if (toppings == null || toppings.isEmpty())
            return BigDecimal.valueOf(0);
        else
            return toppings.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
