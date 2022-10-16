package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.PromotionType;
import lombok.NonNull;

public class PromotionFactory {

    @NonNull
    public static PromotionStrategy createPromotionStrategy(PromotionType promotionType) throws IllegalArgumentException{
        switch (promotionType) {
            case DISCOUNT:
                return new DiscountStrategy();
            case ITEM:
                return new ItemStrategy();
            default:
                throw new IllegalArgumentException("Unknown promotion type: " + promotionType);
        }
    }
}
