package com.example.assignment1.repository;

import com.example.assignment1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByNameContaining(String name);
    public List<Product> findByPriceLessThan(Double price);
}
