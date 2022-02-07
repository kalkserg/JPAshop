package com.example.service;

import com.example.entity.Shop;

public interface ShopService {

    Shop getShopById(Long id);

    Shop getShopByName(String name);

    Long createShop(Shop Shop);

    Shop updateShop(Shop Shop);

    void deleteById(Long id);

    void deleteByName(String name);

    Iterable<Shop> getAllShops();

}
