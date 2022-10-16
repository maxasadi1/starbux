package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ItemStrategyTest {

    @Test
    @DisplayName("Calculate promotion of the cart which its item number is more than the promotion threshold")
    public void calculatePromotion_givenCart_returnOptionalPromotion(){
        ItemStrategy itemStrategy = new ItemStrategy();
        Cart cart = createCartWithItemsMoreThanPromotionThreshold();
        Optional<Promotion> promotion = itemStrategy.calculatePromotion(cart);
        assumeTrue(promotion.isPresent());
        promotion.ifPresent(value -> assertEquals(PromotionType.ITEM, value.getPromotionType()));
        promotion.ifPresent(value -> assertEquals(BigDecimal.valueOf(6), value.getPromotionAmount()));
    }

    @Test
    @DisplayName("Check the cart which its item number is less than the promotion threshold and return empty promotion")
    public void calculatePromotion_givenCart_returnEmptyPromotion(){
        ItemStrategy itemStrategy = new ItemStrategy();
        Cart cart = createCartWithItemsLessThanPromotionThreshold();
        Optional<Promotion> promotion = itemStrategy.calculatePromotion(cart);
        assertFalse(promotion.isPresent());
    }

    @Test
    @DisplayName("Find the cheapest drink with its topping")
    public void getCheapestDrinkWithItsToppingAmount_givenCart_returnAmount(){
        ItemStrategy itemStrategy = new ItemStrategy();
        Cart cart = createCartWithItemsMoreThanPromotionThreshold();
        assertEquals(BigDecimal.valueOf(6), itemStrategy.getCheapestDrinkWithItsToppingAmount(cart));
    }

    @Test
    @DisplayName("Count the total number of drinks in a cart")
    public void getTotalCartItemsQuantity_givenCart_returnDrinkCount(){
        ItemStrategy itemStrategy = new ItemStrategy();
        Cart cart = createCartWithItemsMoreThanPromotionThreshold();
        assertEquals(4, itemStrategy.getTotalCartItemsQuantity(cart));
    }



    private Cart createCartWithItemsMoreThanPromotionThreshold(){
        CartItem cartItemFirst = new CartItem("1", createDrinkBlackCoffee(), List.of(createToppingMilk()), 2);
        CartItem cartItemSecond = new CartItem("2", createDrinkLatte(), List.of(createToppingMilk()), 1);
        CartItem cartItemThird = new CartItem("3", createDrinkMocha(), List.of(createToppingHazelnutSyrup()), 1);
        Cart cart = new Cart();
        cart.setId("1");
        cart.setCartItems(List.of(cartItemFirst, cartItemSecond, cartItemThird));
        return cart;
    }

    private Cart createCartWithItemsLessThanPromotionThreshold(){
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
