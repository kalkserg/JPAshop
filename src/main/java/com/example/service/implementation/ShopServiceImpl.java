package com.example.service.implementation;

import com.example.entity.Shop;
import com.example.repository.ProductRepository;
import com.example.repository.ShopRepository;
import com.example.service.CartService;
import com.example.service.ShopService;
import com.example.service.exception.ThereIsNoSuchShopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.converter.ShopConverter.shopConverter;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartService cartService;


    @Override
    public Shop getShopById(Long id) {
        return shopRepository.findById(id).orElseThrow(()->new ThereIsNoSuchShopException());
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
        return shopRepository.save(shopConverter(Shop, oldShop));
    }

    @Override
    public void deleteById(Long id) {
        Shop shop = shopRepository.findById(id).orElseThrow(() -> new ThereIsNoSuchShopException());
        cartService.deleteAllByShop(shop);
        productRepository.deleteAllByShop(shop);
        shopRepository.deleteShopById(id);
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
