package com.example.assignment1.service;

import com.example.assignment1.model.Product;
import com.example.assignment1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //get all products
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //add new product
    public void createProduct(Product product){
        productRepository.save(product);
    }

    //get product by ID
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    //update product
    public void updateProduct(Long id,Product product){
        Optional<Product> optionalP = productRepository.findById(id);
        if(optionalP.isPresent()){
            Product productToUpdate = optionalP.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setQuantity(product.getQuantity());
            productRepository.save(productToUpdate);
        }
    }

    //delete product
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
