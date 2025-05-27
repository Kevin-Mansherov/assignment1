package com.example.assignment1.controller;

import com.example.assignment1.model.Product;
import com.example.assignment1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") // new*************************
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //get all products
    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/newProduct")
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/searchByName")
    public List<Product> findByNameContaining(@RequestParam String name) {
        return productService.findByNameContaining(name);
    }

    @GetMapping("/searchByPrice")
    public List<Product> findByPriceLessThan(@RequestParam Double price) {
        return productService.findByPriceLessThan(price);
    }
}
