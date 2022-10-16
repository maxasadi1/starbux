package com.backend.sartbux.service.impl.promotion;

import com.backend.sartbux.model.*;
import com.backend.sartbux.model.enums.ProductType;
import com.backend.sartbux.model.enums.PromotionType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class PromotionImplTest {

    @Test
    @DisplayName("Check available promotion for a cart")
    public void checkPromotion_givenCart_returnOptionalPromotion(){
        PromotionImpl promotionImpl = new PromotionImpl();
        Optional<Promotion> promotion =
                promotionImpl.checkPromotion(createCartWithCartItemListIncludesDrinksAndToppings());
        assumeTrue(promotion.isPresent());
        promotion.ifPresent(value -> assertEquals(BigDecimal.valueOf(8), value.getPromotionAmount()));
    }



    @Test
    @DisplayName("Find the promotion with maximum discount amount")
    public void findPromotionWithMaximumAmount_givenPromotionList_returnOptionalPromotion(){
        PromotionImpl promotionImpl = new PromotionImpl();
        Promotion maxPromotion = new Promotion("", BigDecimal.valueOf(8), PromotionType.DISCOUNT);
        Promotion minPromotion = new Promotion("", BigDecimal.valueOf(7), PromotionType.ITEM);
        List<Promotion> promotionList = List.of(minPromotion,maxPromotion);
        promotionImpl.findPromotionWithMaximumAmount(promotionList).ifPresent(value -> assertSame(maxPromotion,value));
    }

    @Test
    @DisplayName("Return one promotion when both promotion discount amount is same")
    public void findPromotionWithMaximumAmount_givenPromotionListSame_returnOptionalPromotion(){
        PromotionImpl promotionImpl = new PromotionImpl();
        Promotion maxPromotion = new Promotion("", BigDecimal.valueOf(8), PromotionType.DISCOUNT);
        Promotion minPromotion = new Promotion("", BigDecimal.valueOf(8), PromotionType.ITEM);
        List<Promotion> promotionList = List.of(minPromotion,maxPromotion);
        promotionImpl.findPromotionWithMaximumAmount(promotionList)
                .ifPresent(value -> assertThat(value).isIn(maxPromotion, minPromotion));
    }

    private Cart createCartWithCartItemListIncludesDrinksAndToppings(){
        CartItem cartItemFirst = new CartItem("1", createDrinkBlackCoffee(), List.of(createToppingMilk(), createToppingHazelnutSyrup()), 2);
        CartItem cartItemSecond = new CartItem("2", createDrinkLatte(), null, 1);
        CartItem cartItemThird = new CartItem("3", createDrinkMocha(), List.of(createToppingHazelnutSyrup()), 1);
        Cart cart = new Cart();
        cart.setId("1");
        cart.setCartItems(List.of(cartItemFirst, cartItemSecond, cartItemThird));
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
