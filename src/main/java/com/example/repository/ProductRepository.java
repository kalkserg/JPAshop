package com.example.repository;

import com.example.entity.Cart;
import com.example.entity.Product;
import com.example.entity.Shop;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long > {

    Iterable<Product> findAllByName(String name);

    @Transactional
    void deleteAllByShop(Shop shop);

}
