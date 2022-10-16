package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.CartItem;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.model.enums.PromotionType;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Optional;

public class ItemStrategy implements PromotionStrategy {
    private final Cart cart;

    public ItemStrategy(Cart cart) {
        this.cart = cart;
    }

    @Override
    public Optional<Promotion> calculatePromotion() {
        if(getTotalCartItemsQuantity() >= 3){
            BigDecimal cheapestDrinkWithItsTopping = getCheapestDrinkWithItsToppingAmount();
            return Optional.of(new Promotion("", cheapestDrinkWithItsTopping, PromotionType.ITEM));
        }else
            return Optional.empty();
    }

    public BigDecimal getCheapestDrinkWithItsToppingAmount(){
       return cart.getCartItems().stream().map(cartIem -> cartIem.getDrink().getPrice().add(cartIem.getToppingAmount())).min(Comparator.naturalOrder()).orElse(BigDecimal.valueOf(0));
    }

    public int getTotalCartItemsQuantity(){
        return cart.getCartItems().stream().map(CartItem::getQuantity).reduce(0, Integer::sum);
    }
}
