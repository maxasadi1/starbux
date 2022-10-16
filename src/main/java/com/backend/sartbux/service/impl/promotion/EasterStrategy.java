package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.model.enums.PromotionType;

import java.math.BigDecimal;
import java.util.Optional;

public class EasterStrategy implements PromotionStrategy{

    @Override
    public Optional<Promotion> calculatePromotion() {
        return Optional.empty();
    }
}
