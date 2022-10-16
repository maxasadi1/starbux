package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.Cart;
import com.backend.sartbux.model.Promotion;
import com.backend.sartbux.model.PromotionType;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class DiscountStrategy implements PromotionStrategy{
    @Override
    public Optional<Promotion> calculatePromotion(Cart cart) {
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
