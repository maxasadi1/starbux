package com.backend.sartbux.model;

import com.backend.sartbux.exception.CartItemException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

public class CartTest {
    Cart cart;

    @Test
    @DisplayName("Total amount of cart items in a cart")
    public void getCartAmount_cartWithCartItemListIncludesDrinksAndToppings_amount(){
        cart = createCartWithCartItemListIncludesDrinksAndToppings();
        assertEquals(BigDecimal.valueOf(32), cart.getCartAmount());
    }

    @Test
    @DisplayName("All cart items should contain at least one drink to get total amount of a cart")
    public void getCartAmount_cartWithCartItemsOneCartItemHasNoDrink_throwException(){
        cart = createCartWithCartItemListOneCartItemHasNoDrink();
        assertThrows(CartItemException.class, cart::getCartAmount);
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

    private Cart createCartWithCartItemListOneCartItemHasNoDrink(){
        CartItem cartItemFirst = new CartItem("1", createDrinkBlackCoffee(), List.of(createToppingMilk(), createToppingHazelnutSyrup()), 2);
        CartItem cartItemSecond = new CartItem("2", null, null, 1);
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
