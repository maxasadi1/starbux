package com.backend.sartbux.service.impl.promotion;


import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.model.enums.PromotionType;

import java.math.BigDecimal;
import java.util.Optional;

public interface PromotionStrategy {
    public Optional<Promotion> calculatePromotion();
}
