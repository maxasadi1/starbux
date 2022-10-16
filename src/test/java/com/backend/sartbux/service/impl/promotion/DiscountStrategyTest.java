package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.*;
import com.backend.sartbux.model.enums.ProductType;
import com.backend.sartbux.model.enums.PromotionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class DiscountStrategyTest {

    @Test
    @DisplayName("Calculate promotion the cart which its total amount is more than the discount threshold")
    public void calculatePromotion_givenCart_returnOptionalPromotion(){
        Cart cart = createCartWithTotalAmountMoreThanDiscountThreshold();
        DiscountStrategy discountStrategy = new DiscountStrategy(cart);
        Optional<Promotion> promotion = discountStrategy.calculatePromotion();
        assumeTrue(promotion.isPresent());
        promotion.ifPresent(value -> assertEquals(PromotionType.DISCOUNT, value.getPromotionType()));
        promotion.ifPresent(value -> assertEquals(BigDecimal.valueOf(8), value.getPromotionAmount()));
    }

    @Test
    @DisplayName("Check the cart which its total amount is less than the discount threshold and return empty promotion")
    public void calculatePromotion_givenCart_returnEmptyPromotion(){
        Cart cart = createCartWithTotalAmountLessThanDiscountThreshold();
        DiscountStrategy discountStrategy = new DiscountStrategy(cart);
        Optional<Promotion> promotion = discountStrategy.calculatePromotion();
        assertFalse(promotion.isPresent());
    }

    private Cart createCartWithTotalAmountMoreThanDiscountThreshold(){
        CartItem cartItemFirst = new CartItem("1", createDrinkBlackCoffee(), List.of(createToppingMilk(), createToppingHazelnutSyrup()), 2);
        CartItem cartItemSecond = new CartItem("2", createDrinkLatte(), null, 1);
        CartItem cartItemThird = new CartItem("3", createDrinkMocha(), List.of(createToppingHazelnutSyrup()), 1);
        Cart cart = new Cart();
        cart.setId("1");
        cart.setCartItems(List.of(cartItemFirst, cartItemSecond, cartItemThird));
        return cart;
    }

    private Cart createCartWithTotalAmountLessThanDiscountThreshold(){
        CartItem cartItemFirst = new CartItem("1", createDrinkBlackCoffee(), List.of(createToppingMilk(), createToppingHazelnutSyrup()), 1);
        Cart cart = new Cart();
        cart.setId("1");
        cart.setCartItems(List.of(cartItemFirst));
        return cart;
    }

    private Product createDrinkBlackCoffee(){
        return new Product("1", "Black Coffee" , BigDecimal.valueOf(4), ProductType.DRINK);
    }

    private Product createDrinkLatte(){
        return new Product("2", "Latte" , BigDecimal.valueOf(5), ProductType.DRINK);
    }

    private Product createDrinkMocha(){
        return new Product("3", "Mocha" , BigDecimal.valueOf(6), ProductType.DRINK);
    }

    private Product createToppingMilk(){
        return new Product("1", "Milk" , BigDecimal.valueOf(2), ProductType.DRINK);
    }
    private Product createToppingHazelnutSyrup(){
        return new Product("2", "Hazelnut syrup" , BigDecimal.valueOf(3), ProductType.DRINK);
    }
}
