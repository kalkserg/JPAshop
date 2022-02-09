package com.example.service.implementation;

import com.example.entity.Product;
import com.example.entity.Shop;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import com.example.service.ShopService;
import com.example.service.exception.ThereIsNoSuchProductException;
import com.example.service.exception.ThereIsNoSuchShopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.converter.ProductConverter.productConverter;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopService shopService;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ThereIsNoSuchProductException());
    }

    @Override
    public Iterable<Product> getProductsByName(String name) {
        Iterable<Product> products = productRepository.findAllByName(name);
        return products;
    }

    @Override
    public Long createProduct(Product product) {
        Shop shop = shopService.getShopByName(product.getShop().getName());
        if (shop == null) {
            throw new ThereIsNoSuchShopException();
        }
        product.setShop(shop);
        return productRepository.save(product).getId();
    }

    @Override
    public Product updateProduct(Product product) {
        Product oldProduct = productRepository.findById(product.getId()).orElseGet(Product::new);
        return productRepository.save(productConverter(product, oldProduct));
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ThereIsNoSuchProductException();
        }
    }

    @Override
    public void deleteAllByShop(Shop shop) {
        try {
            productRepository.deleteAllByShop(shop);
        } catch (Exception ex) {
            throw new ThereIsNoSuchShopException();
        }
    }
}
