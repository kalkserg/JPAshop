package com.example.converter;

import com.example.entity.Shop;

public final class ShopConverter {

    public static Shop shopConverter(Shop updateShop, Shop savedShop) {
        savedShop.setName(updateShop.getName());
        return savedShop;
    }
}
