package com.example.assignment1.repository;

import com.example.assignment1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Product findByNameContaining(String name);
    public Product findByPriceLessThan(Double price);
}
