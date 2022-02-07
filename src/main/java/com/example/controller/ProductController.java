package com.example.controller;

import com.example.entity.Product;
import com.example.service.ProductService;
import com.example.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping(path = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
class ProductController {

    private ProductService productService;
    @Autowired
    private ShopService shopService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Long createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/update")
    public Product updateProduct(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping(value = "/delete/shop/{id}")
    public void delProductByShop(@PathVariable String id) {
        productService.deleteAllByShop(shopService.getShopById(Long.parseLong(id)));
    }

    @GetMapping(value = "/delete/{id}")
    public void delProductById(@PathVariable String id) {
        productService.deleteById(Long.parseLong(id));
    }

    @GetMapping(value = "/getAll")
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/get/{name}")
    public Iterable<Product> getProductByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

}
