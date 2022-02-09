package com.example.service.implementation;

import com.example.entity.Cart;
import com.example.entity.Person;
import com.example.entity.Product;
import com.example.entity.Shop;
import com.example.repository.CartRepository;
import com.example.service.CartService;
import com.example.service.exception.ThereIsNoSuchCartException;
import com.example.service.exception.ThereIsNoSuchProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Long createCart(Cart cart) {
        return cartRepository.save(cart).getId();
    }

    @Override
    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public boolean deleteCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cartRepository.delete(cart);
            return true;
        } else {
            throw new ThereIsNoSuchCartException();
        }
    }

    @Override
    public List<Cart> getCartByPerson(Person person) {
        List<Cart> allByPerson = cartRepository.findAllByPerson(person);
        return allByPerson;
    }

    @Override
    public Cart getCartByPersonAndShop(Person person, Shop shop) {
        Optional<Cart> cart = cartRepository.findCartByPersonAndShop(person, shop);
        return cart.orElseThrow(() -> new ThereIsNoSuchCartException());
    }

    @Override
    public Cart getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.orElseThrow(() -> new ThereIsNoSuchCartException());
    }

    @Override
    public List<Cart> getAllCartByShop(Shop shop) {
        List<Cart> allByShop = cartRepository.findAllByShop(shop);
        return allByShop;
    }

    @Override
    public String addProduct(Person person, Product product) {
        Cart cart = cartRepository.findCartByPersonAndShop(person, product.getShop()).orElse(null);
        try {
            List<Product> products = cart.getProducts();
            products.add(product);
            cart.setProducts(products);
            cart.calculateSum();
            cartRepository.save(cart);
            return cart.getSum();
        } catch (Exception ex) {
            throw new ThereIsNoSuchProductException();
        }
    }

    @Override
    public String delProduct(Person person, Product product) {
        Cart cart = cartRepository.findCartByPersonAndShop(person, product.getShop()).orElse(null);
        try {
            List<Product> products = cart.getProducts();
            products.remove(product);
            cart.setProducts(products);
            cart.calculateSum();
            cartRepository.save(cart);
            return cart.getSum();
        } catch (Exception ex) {
            throw new ThereIsNoSuchProductException();
        }
    }

    @Override
    public void deleteAllByShop(Shop shop) {
        cartRepository.deleteAllByShop(shop);
    }
}
