package com.backend.sartbux.service.impl.promotion;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.enums.PromotionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test PromotionFactory Class")
public class PromotionFactoryTest {

    @Test
    @DisplayName("Create instance of promotion strategy regards of given promotion type")
    public void createPromotionStrategy_givenPromotionType_returnPromotionStrategy(){
        Cart cart = new Cart();
        for (PromotionType promotionType: PromotionType.values()) {
            assertInstanceOf(PromotionStrategy.class, PromotionFactory.createPromotionStrategy(promotionType, cart));
        }
    }
}