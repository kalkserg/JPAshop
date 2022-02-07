package com.example.controller;

import com.example.entity.Cart;
import com.example.entity.Person;
import com.example.entity.Product;
import com.example.service.CartService;
import com.example.service.PersonService;
import com.example.service.ProductService;
import com.example.service.ShopService;
import com.example.service.exception.ThereIsNoSuchCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Validated
@RestController
@RequestMapping
class CartController {

    private ShopService shopService;
    private PersonService personService;
    private ProductService productService;
    private CartService cartService;

    @Autowired
    public void setShopService(ShopService shopService, ProductService productService, PersonService personService, CartService cartService) {
        this.shopService = shopService;
        this.personService = personService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @PostMapping("/person/{idPerson}/buy/product/{idProduct}")
    public String buyProduct(@PathVariable String idPerson, @PathVariable String idProduct) {
        Person person = personService.getPersonById(Long.parseLong(idPerson));
        Product product = productService.getProductById(Long.parseLong(idProduct));
        Cart cart = null;
        try {
            cartService.getCartByPersonAndShop(person, product.getShop());
        } catch (ThereIsNoSuchCartException ex) {
            cart = new Cart();
            cart.setShop(product.getShop());
            cart.setPerson(person);
            cart.setProducts(new ArrayList<>());
            cartService.createCart(cart);
        }
        return cartService.addProduct(person, product);
    }

    @PostMapping("/person/{idPerson}/cancel/product/{idProduct}")
    public String cancelProduct(@PathVariable String idPerson, @PathVariable String idProduct) {
        Product product = productService.getProductById(Long.parseLong(idProduct));
        Person person = personService.getPersonById(Long.parseLong(idPerson));
        Cart cart = cartService.getCartByPersonAndShop(person, product.getShop());
        return cartService.delProduct(person, product);
    }

    @GetMapping(value = "/person/{idPerson}/cart/getAll")
    public Iterable<Cart> getAllCarts(@PathVariable String idPerson) {
        Person person = personService.getPersonById(Long.parseLong(idPerson));
        return cartService.getCartByPerson(person);
    }

    @GetMapping(value = "/cart/delete/{idCart}")
    public boolean deleteCart(@PathVariable String idCart) {
        return cartService.deleteCartById(Long.parseLong(idCart));
    }

    @GetMapping(value = "/cart/get/{idCart}")
    public Cart getCart(@PathVariable String idCart) {
        return cartService.getCartById(Long.parseLong(idCart));
    }

}
