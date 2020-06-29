package com.xcw.test;

import com.xcw.pojo.Cart;
import com.xcw.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart = new Cart();

    @Test
    public void addItem() {
        cart.addItem(new CartItem(1, "www", 3, new BigDecimal(100)));
        cart.addItem(new CartItem(1, "www", 9, new BigDecimal(100)));
        cart.addItem(new CartItem(2, "ddd", 2, new BigDecimal(200)));
        cart.addItem(new CartItem(3, "aaa", 1, new BigDecimal(400)));

        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1, "www", 3, new BigDecimal(100)));
        cart.addItem(new CartItem(1, "www", 9, new BigDecimal(100)));
        cart.addItem(new CartItem(2, "ddd", 2, new BigDecimal(200)));
        cart.addItem(new CartItem(3, "aaa", 1, new BigDecimal(400)));

        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1, "www", 3, new BigDecimal(100)));
        cart.addItem(new CartItem(1, "www", 9, new BigDecimal(100)));
        cart.addItem(new CartItem(2, "ddd", 2, new BigDecimal(200)));
        cart.addItem(new CartItem(3, "aaa", 1, new BigDecimal(400)));

        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateNum() {
        cart.addItem(new CartItem(1, "www", 3, new BigDecimal(100)));
        cart.addItem(new CartItem(1, "www", 9, new BigDecimal(100)));
        cart.addItem(new CartItem(2, "ddd", 2, new BigDecimal(200)));
        cart.addItem(new CartItem(3, "aaa", 1, new BigDecimal(400)));

        System.out.println(cart);

        cart.updateNum(3, 300);
        System.out.println(cart);
    }
}