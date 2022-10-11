package com.backend.sartbux.service.impl.promotion;


import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;

import java.util.Optional;

//TODO What should we name the class
public interface PromotionInterface {
    public Optional<Promotion> calculatePromotion(Cart cart);
}
