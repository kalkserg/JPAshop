package com.example.service.implementation;

import com.example.entity.Shop;
import com.example.repository.ProductRepository;
import com.example.repository.ShopRepository;
import com.example.service.CartService;
import com.example.service.ShopService;
import com.example.service.exception.ShopNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.converter.ShopConverter.ShopConverter;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }


    @Override
    public Shop getShopById(Long id) {
        Optional<Shop> Shop = shopRepository.findById(id);
        return Shop.orElseGet(Shop::new);
    }

    @Override
    public Shop getShopByName(String name) {
        return shopRepository.findByName(name);
    }

    @Override
    public Long createShop(Shop Shop) {
        return shopRepository.save(Shop).getId();
    }

    @Override
    public Shop updateShop(Shop Shop) {
        Shop oldShop = shopRepository.findById(Shop.getId()).orElseGet(Shop::new);
        return shopRepository.save(ShopConverter(Shop, oldShop));
    }

    @Override
    public void deleteById(Long id) {
        Shop shop = shopRepository.findById(id).orElse(null);
        if (shop != null) {
            cartService.deleteAllByShop(shop);
            productRepository.deleteAllByShop(shop);
            shopRepository.deleteShopById(id);
        } else {
            throw new ShopNotFoundException();
        }
    }

    @Override
    public void deleteByName(String name) {
        shopRepository.deleteShopByName(name);
    }

    @Override
    public Iterable<Shop> getAllShops() {
        return shopRepository.findAll();
    }

}
