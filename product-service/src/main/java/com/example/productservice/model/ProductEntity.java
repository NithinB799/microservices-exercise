package com.example.productservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer stock;
}
