package com.example.desafiogfxisrael.domain;

public class Product {
    private final int id;
    private final String title;
    private final double price;
    private final String description;
    private final Category category;
    private final String image;

    public Product(int id, String title, double price, String description, Category category, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}
