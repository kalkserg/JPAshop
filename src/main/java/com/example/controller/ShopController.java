package com.example.controller;

import com.example.entity.Shop;
import com.example.service.CartService;
import com.example.service.ProductService;
import com.example.service.ShopService;
import com.example.service.exception.ShopNotFoundException;
import com.example.service.exception.UniqueShopNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "/shop", produces = MediaType.APPLICATION_JSON_VALUE)
class ShopController {

    private ShopService shopService;
    private ProductService productService;
    private CartService cartService;

    @Autowired
    public void setShopService(ShopService shopService, ProductService productService, CartService cartService) {
        this.shopService = shopService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/create")
    public Long createNewShop(@Valid @RequestBody Shop Shop) {
        try {
            return shopService.createShop(Shop);
        } catch (Exception ex) {
            throw new UniqueShopNameException();
        }
    }

    @PostMapping("/update")
    public Shop updateShop(@Valid @RequestBody Shop Shop) {
        return shopService.updateShop(Shop);
    }

    @PostMapping(value = "/delete")
    public void delShopByName(@Valid @RequestBody Shop shop) {
        if (shopService.getShopByName(shop.getName()) == null) {
            throw new ShopNotFoundException();
        } else {
            cartService.deleteAllByShop(shop);
            productService.deleteAllByShop(shop);
            shopService.deleteByName(shop.getName());
        }
    }

    @GetMapping(value = "/delete/{id}")
    public void delShopById(@PathVariable String id) {
        shopService.deleteById(Long.parseLong(id));
    }

    @GetMapping(value = "/getAll")
    public Iterable<Shop> getAllShops() {
        return shopService.getAllShops();
    }

}
