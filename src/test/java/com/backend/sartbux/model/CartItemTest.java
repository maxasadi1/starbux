package com.backend.sartbux.model;

import com.backend.sartbux.exception.CartItemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

public class CartItemTest {

    @Test
    @DisplayName("Total Amount of a cart item with a drink and a topping")
    public void getItemAmount_cartItemWithDrinkWithTopping_amount(){
        CartItem cartItem = createCartItemDrinkAndTopping();
        Assertions.assertEquals(cartItem.getItemAmount(), BigDecimal.valueOf(6));
    }

    @Test
    @DisplayName("Total Amount of a cart item with a drink and without any topping")
    public void getItemAmount_cartItemWithDrinkWithoutTopping_amount(){
        CartItem cartItem = createCartItemDrinkWithoutTopping();
        Assertions.assertEquals(cartItem.getItemAmount(), BigDecimal.valueOf(4));
    }

    @Test
    @DisplayName("Cart item should contains at least one drink in order to get total amount")
    public void getItemAmount_cartItemWithToppingWithoutDrink_throwException(){
        CartItem cartItem = createCartItemWithToppingWithoutDrink();
        Assertions.assertThrows(CartItemException.class, cartItem::getItemAmount, "There is no drink in this cart item.");
    }

    @Test
    @DisplayName("Total amount of a topping list in a cart item")
    public void getToppingsAmount_cartItemWithDrinkWithTwoTopping_amount(){
        CartItem cartItem = createCartItemWithDrinkAndTwoToppings();
        Assertions.assertEquals(cartItem.getToppingsAmount(), BigDecimal.valueOf(5));
    }

    @Test
    @DisplayName("Total amount of toppings when there is no topping in a cart item")
    public void getToppingsAmount_cartItemWithNoTopping_amount(){
        CartItem cartItem = createCartItemDrinkWithoutTopping();
        Assertions.assertEquals(cartItem.getToppingsAmount(), BigDecimal.valueOf(0));
    }

    private CartItem createCartItemDrinkAndTopping(){
        Product product = new Product("1", "Black Coffee" , BigDecimal.valueOf(4), ProductType.DRINK);
        Product topping = new Product("1", "Milk" , BigDecimal.valueOf(2), ProductType.DRINK);
        return new CartItem("1", product, List.of(topping) ,1);
    }

    private CartItem createCartItemDrinkWithoutTopping(){
        Product product = new Product("1", "Black Coffee" , BigDecimal.valueOf(4), ProductType.DRINK);
        return new CartItem("1", product, null,1);
    }
    private CartItem createCartItemWithToppingWithoutDrink(){
        Product topping = new Product("1", "Milk" , BigDecimal.valueOf(2), ProductType.DRINK);
        return new CartItem("1", null, List.of(topping),1);
    }

    private CartItem createCartItemWithDrinkAndTwoToppings(){
        Product product = new Product("1", "Black Coffee" , BigDecimal.valueOf(4), ProductType.DRINK);
        Product toppingMilk = new Product("1", "Milk" , BigDecimal.valueOf(2), ProductType.DRINK);
        Product toppingHazelnutSyrup = new Product("2", "Hazelnut syrup" , BigDecimal.valueOf(3), ProductType.DRINK);
        return new CartItem("1", product, List.of(toppingMilk, toppingHazelnutSyrup) ,1);
    }
}
