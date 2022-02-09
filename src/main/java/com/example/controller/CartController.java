package com.example.controller;

import com.example.entity.Cart;
import com.example.entity.Person;
import com.example.entity.Product;
import com.example.service.CartService;
import com.example.service.PersonService;
import com.example.service.ProductService;
import com.example.service.exception.ThereIsNoSuchCartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Validated
@RestController
@RequestMapping
class CartController {

    @Autowired
    private PersonService personService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;


    /**
     * Adds a product to the Person's Cart. If the Person does not have a Cart in this Shop, then a new Cart is created for this Shop.
     *
     * @param idPerson  unique Person ID
     * @param idProduct unique Product ID
     * @return - the cost of the Products in the Cart
     */
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


    /**
     * Cancels adding an item to the cart.
     *
     * @param idPerson  unique Person ID
     * @param idProduct unique Product ID
     * @return - the cost of the Products in the Cart
     */
    @PostMapping("/person/{idPerson}/cancel/product/{idProduct}")
    public String cancelProduct(@PathVariable String idPerson, @PathVariable String idProduct) {
        Product product = productService.getProductById(Long.parseLong(idProduct));
        Person person = personService.getPersonById(Long.parseLong(idPerson));
        Cart cart = cartService.getCartByPersonAndShop(person, product.getShop());
        return cartService.delProduct(person, product);
    }


    /**
     * Getting all the Person's Carts from different Shops.
     *
     * @param idPerson unique Person ID
     * @return - the cost of the Products in the Cart
     */
    @GetMapping(value = "/person/{idPerson}/cart/getAll")
    public Iterable<Cart> getAllCarts(@PathVariable String idPerson) {
        Person person = personService.getPersonById(Long.parseLong(idPerson));
        return cartService.getCartByPerson(person);
    }

    /**
     * Deleting a cart by Id.
     *
     * @param idCart unique Cart ID
     * @return - the cost of the Products in the Cart
     */
    @GetMapping(value = "/cart/delete/{idCart}")
    public boolean deleteCart(@PathVariable String idCart) {
        return cartService.deleteCartById(Long.parseLong(idCart));
    }

    /**
     * Get cart by Id.
     *
     * @param idCart unique Cart ID
     * @return - the cost of the Products in the Cart
     */
    @GetMapping(value = "/cart/get/{idCart}")
    public Cart getCart(@PathVariable String idCart) {
        return cartService.getCartById(Long.parseLong(idCart));
    }

}
