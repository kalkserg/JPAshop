package com.example.converter;

import com.example.entity.Product;

public final class ProductConverter {

    public static Product ProductConverter(Product updateProduct, Product savedProduct) {
        savedProduct.setName(updateProduct.getName());
        savedProduct.setPrice(updateProduct.getPrice());
        savedProduct.setShop(updateProduct.getShop());
        return savedProduct;
    }
}
