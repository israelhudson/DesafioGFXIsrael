package com.example.desafiogfxisrael.model;

import com.example.desafiogfxisrael.domain.Category;
import com.example.desafiogfxisrael.domain.Product;

public class ProductModel {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    public Product toDomain() {
        return new Product(
                id,
                title,
                price,
                description,
                Category.fromApiValue(category),
                image
        );
    }
}
