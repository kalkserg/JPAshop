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

    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;

    /**
     * Create new Product in the Shop
     *
     * @param product in JSON, fill in the attributes of the Product class.
     * @return Id of created Product
     */
    @PostMapping("/create")
    public Long createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    /**
     * Update the Product object with a unique Id.
     *
     * @param product in JSON, fill in the attributes of the Product class.
     * @return updated Product
     */
    @PostMapping("/update")
    public Product updateProduct(@Valid @RequestBody Product product) {
        return productService.updateProduct(product);
    }

    /**
     * Delete Product by Id
     *
     * @param idProduct unique Product Id
     */
    @GetMapping(value = "/delete/{idProduct}")
    public void delProductById(@PathVariable String idProduct) {
        productService.deleteById(Long.parseLong(idProduct));
    }

    /**
     * Get list of all Products
     *
     * @return List of all Products
     */
    @GetMapping(value = "/getAll")
    public Iterable<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Get list of Products by name
     *
     * @param name name of Products
     * @return list of Products
     */
    @GetMapping(value = "/get/{name}")
    public Iterable<Product> getProductByName(@PathVariable String name) {
        return productService.getProductsByName(name);
    }

}
