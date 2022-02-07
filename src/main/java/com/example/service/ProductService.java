package com.example.service;

import com.example.entity.Product;
import com.example.entity.Shop;

public interface ProductService {

    Product getProductById(Long id);

    Long createProduct(Product product);

    Product updateProduct(Product product);

    Iterable<Product> getAllProducts();

    Iterable<Product> getProductsByName(String name);

    void deleteById(Long id);

    void deleteAllByShop(Shop shop);
}
