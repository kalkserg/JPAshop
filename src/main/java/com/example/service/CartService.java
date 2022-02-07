package com.example.service;

import com.example.entity.Cart;
import com.example.entity.Person;
import com.example.entity.Product;
import com.example.entity.Shop;

public interface CartService {

    Long createCart(Cart cart);

    void deleteCart(Cart cart);

    boolean deleteCartById(Long id);

    Cart getCartById(Long id);

    Iterable<Cart> getAllCartByShop(Shop shop);

    Iterable<Cart> getCartByPerson(Person person);

    Cart getCartByPersonAndShop(Person person, Shop shop);

    String addProduct(Person person, Product product);

    String delProduct(Person person, Product product);

    void deleteAllByShop(Shop shop);
}
