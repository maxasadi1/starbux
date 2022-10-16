package com.backend.sartbux.service.impl.promotion;


import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;

import java.util.Optional;

public interface PromotionStrategy {
    public Optional<Promotion> calculatePromotion(Cart cart);
}
