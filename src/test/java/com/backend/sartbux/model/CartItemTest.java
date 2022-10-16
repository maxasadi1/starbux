package com.backend.sartbux.model;

import com.backend.sartbux.exception.CartItemException;
import static org.junit.jupiter.api.Assertions.*;

import com.backend.sartbux.model.enums.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CartItemTest {
    CartItem cartItem;

    @Test
    @DisplayName("Total Amount of a cart item with a drink and a topping")
    public void getItemAmount_givenCartItemWithDrinkWithTopping_expectAmount(){
        cartItem = createCartItemDrinkAndTopping();
        assertEquals( BigDecimal.valueOf(6), cartItem.getItemAmount());
    }

    @Test
    @DisplayName("Total Amount of a cart item with a drink and without any topping")
    public void getItemAmount_cartItemWithDrinkWithoutTopping_amount(){
        cartItem = createCartItemDrinkWithoutTopping();
        assertEquals(BigDecimal.valueOf(4), cartItem.getItemAmount());
    }

    @Test
    @DisplayName("Cart item should contains at least one drink in order to get total amount")
    public void getItemAmount_cartItemWithToppingWithoutDrink_throwException(){
        cartItem = createCartItemWithToppingWithoutDrink();
        assertThrows(CartItemException.class, cartItem::getItemAmount);
    }

    @Test
    @DisplayName("Total amount of a topping list in a cart item")
    public void getToppingsAmount_cartItemWithDrinkWithTwoTopping_amount(){
        cartItem = createCartItemWithDrinkAndTwoToppings();
        assertEquals(BigDecimal.valueOf(5), cartItem.getToppingAmount());
    }

    @Test
    @DisplayName("Total amount of toppings when there is no topping in a cart item")
    public void getToppingsAmount_cartItemWithNoTopping_amount(){
        cartItem = createCartItemDrinkWithoutTopping();
        assertEquals(BigDecimal.valueOf(0), cartItem.getToppingAmount());
    }

    @Test
    public void getDrinkAmount(){
        cartItem = createCartItemDrinkWithoutTopping();
        List<String> toppings = cartItem.getToppings().stream().map(Product::getName).collect(Collectors.toList());

    }
    private CartItem createCartItemDrinkAndTopping(){
        Product drink = createDrinkBlackCoffee();
        Product topping = createToppingMilk();
        return new CartItem("1", drink, List.of(topping) ,1);
    }

    private CartItem createCartItemDrinkWithoutTopping(){
        Product drink = createDrinkBlackCoffee();
        return new CartItem("1", drink, null,1);
    }
    private CartItem createCartItemWithToppingWithoutDrink(){
        Product topping = createToppingMilk();
        return new CartItem("1", null, List.of(topping),1);
    }

    private CartItem createCartItemWithDrinkAndTwoToppings(){
        Product drink = createDrinkBlackCoffee();
        Product toppingMilk = createToppingMilk();
        Product toppingHazelnutSyrup = createToppingHazelnutSyrup();
        return new CartItem("1", drink, List.of(toppingMilk, toppingHazelnutSyrup) ,1);
    }

    private Product createDrinkBlackCoffee(){
        return new Product("1", "Black Coffee" , BigDecimal.valueOf(4), ProductType.DRINK);
    }

    private Product createToppingMilk(){
        return new Product("1", "Milk" , BigDecimal.valueOf(2), ProductType.DRINK);
    }
    private Product createToppingHazelnutSyrup(){
        return new Product("2", "Hazelnut syrup" , BigDecimal.valueOf(3), ProductType.DRINK);
    }
}
