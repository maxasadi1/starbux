package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.model.PromotionType;
import com.backend.sartbux.service.PromotionService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PromotionImpl implements PromotionService {

    @Override
    public Optional<Promotion> checkPromotion(Cart cart) {
        List<Promotion> promotionList = new ArrayList<>();

        for (PromotionType promotionType: PromotionType.values()) {
            PromotionStrategy promotionStrategy  = PromotionFactory.createPromotionStrategy(promotionType);
            Optional<Promotion> promotion = promotionStrategy.calculatePromotion(cart);
            promotion.ifPresent(promotionList::add);
        }
        return findPromotionWithMaximumAmount(promotionList);
    }

    public Optional<Promotion> findPromotionWithMaximumAmount(List<Promotion> promotionList){
       return promotionList.stream().max(Comparator.comparing(Promotion::getPromotionAmount));
    }
}
