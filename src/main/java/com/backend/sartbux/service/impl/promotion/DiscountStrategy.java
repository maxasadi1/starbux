package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.model.enums.PromotionType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class DiscountStrategy implements PromotionStrategy{
    private final Cart cart;

    public DiscountStrategy(Cart cart) {
        this.cart = cart;
    }

    @Override
    public Optional<Promotion> calculatePromotion() {
        BigDecimal cartAmount = cart.getCartAmount();
        switch (cartAmount.compareTo(BigDecimal.valueOf(12))){
            case 0:
            case 1:
                return Optional.of(new Promotion("", percentage(cartAmount, BigDecimal.valueOf(25)), PromotionType.DISCOUNT));

            default:
                return Optional.empty();
        }
    }

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct){
        return base.multiply(pct).divide(ONE_HUNDRED, RoundingMode.HALF_EVEN);
    }
}
