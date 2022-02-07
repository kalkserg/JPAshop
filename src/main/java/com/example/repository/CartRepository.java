package com.example.repository;

import com.example.entity.Cart;
import com.example.entity.Person;
import com.example.entity.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

    List<Cart> findAllByShop(Shop shop);

    Optional<Cart> findCartByPersonAndShop(Person person, Shop shop);

    List<Cart> findAllByPerson(Person person);

    @Transactional
    void deleteAllByShop(Shop shop);

}
