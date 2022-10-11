package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.service.PromotionService;


import java.util.Optional;

public class PromotionItemImpl implements PromotionInterface {

    @Override
    public Optional<Promotion> calculatePromotion(Cart cart) {
        return Optional.empty();
    }
}
