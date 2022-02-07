package com.example.repository;

import com.example.entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Long> {

    @Transactional
    void deleteShopByName(String name);

    @Transactional
    void deleteShopById(Long id);

    Shop findByName(String name);

}
