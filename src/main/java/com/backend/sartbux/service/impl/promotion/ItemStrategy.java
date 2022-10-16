package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.CartItem;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.model.PromotionType;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;

public class ItemStrategy implements PromotionStrategy {
    @Override
    public Optional<Promotion> calculatePromotion(Cart cart) {
        if(getTotalCartItemsQuantity(cart) >= 3){
            BigDecimal cheapestDrinkWithItsTopping = getCheapestDrinkWithItsToppingAmount(cart);
            return Optional.of(new Promotion("", cheapestDrinkWithItsTopping, PromotionType.ITEM));
        }else
            return Optional.empty();
    }

    public BigDecimal getCheapestDrinkWithItsToppingAmount(Cart cart){
       return cart.getCartItems().stream().map(cartIem -> cartIem.getDrink().getPrice().add(cartIem.getToppingAmount())).min(Comparator.naturalOrder()).orElse(BigDecimal.valueOf(0));
    }

    public int getTotalCartItemsQuantity(Cart cart){
        return cart.getCartItems().stream().map(CartItem::getQuantity).reduce(0, Integer::sum);
    }
}
