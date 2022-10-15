package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.service.PromotionService;

import java.util.Optional;

public class PromotionImpl implements PromotionService {

    @Override
    public Optional<Promotion> checkPromotion(Cart cart) {
        //TODO cart size > 3

        //TODO total cart amount >=12 

        return Optional.empty();
    }
}
