package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.enums.PromotionType;
import lombok.NonNull;

public class PromotionFactory {

    @NonNull
    public static PromotionStrategy createPromotionStrategy(PromotionType promotionType, Cart cart) throws IllegalArgumentException{
        switch (promotionType) {
            case DISCOUNT:
                return new DiscountStrategy(cart);
            case ITEM:
                return new ItemStrategy(cart);
            case EASTER:
                return new EasterStrategy();
            default:
                throw new IllegalArgumentException("Unknown promotion type: " + promotionType);
        }
    }
}
