package com.example.assignment1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue
    private long id;

    @Column(name="name",nullable=true)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="quantity")
    private int quantity;

    public Product(long id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    public Product() {

    }

}
