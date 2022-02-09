package com.example.controller;

import com.example.entity.Shop;
import com.example.service.CartService;
import com.example.service.ProductService;
import com.example.service.ShopService;
import com.example.service.exception.ThereIsNoSuchShopException;
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

    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;


    /**
     * Create new Shop
     *
     * @param shop in JSON, fill in the attributes of the Shop class.
     * @return Id of сreated Shop
     */
    @PostMapping("/create")
    public Long createNewShop(@Valid @RequestBody Shop shop) {
        try {
            return shopService.createShop(shop);
        } catch (Exception ex) {
            throw new UniqueShopNameException();
        }
    }

    /**
     * Update the Shop object with a unique Id.
     *
     * @param shop in JSON, fill in the attributes of the Shop class.
     * @return updated Shop
     */
    @PostMapping("/update")
    public Shop updateShop(@Valid @RequestBody Shop shop) {
        return shopService.updateShop(shop);
    }

    @PostMapping(value = "/delete")
    public void delShopByName(@Valid @RequestBody Shop shop) {
        if (shopService.getShopByName(shop.getName()) == null) {
            throw new ThereIsNoSuchShopException();
        } else {
            cartService.deleteAllByShop(shop);
            productService.deleteAllByShop(shop);
            shopService.deleteByName(shop.getName());
        }
    }

    /**
     * Delete Shop by Id
     *
     * @param idShop unique Shop Id
     */
    @GetMapping(value = "/delete/{idShop}")
    public void delShopById(@PathVariable String idShop) {
        shopService.deleteById(Long.parseLong(idShop));
    }

    /**
     * Get list 0f all Shops
     *
     * @return list of all Shops
     */
    @GetMapping(value = "/getAll")
    public Iterable<Shop> getAllShops() {
        return shopService.getAllShops();
    }

}
