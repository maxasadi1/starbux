package com.backend.sartbux.service;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;

import java.util.Optional;

public interface PromotionService {
    Optional<Promotion> checkPromotion(Cart cart);
}
