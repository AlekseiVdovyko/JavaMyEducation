package ru.edu;

import java.time.LocalDate;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private String description;
    private ProductCategory category;
    private LocalDate manufactureDateTime;
    private String manufacturer;
    private boolean hasExpiryTime;
    private int stock;

    public Product(UUID id, String name, String description, ProductCategory category,
                   LocalDate manufactureDateTime, String manufacturer,
                   boolean hasExpiryTime, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.manufactureDateTime = manufactureDateTime;
        this.manufacturer = manufacturer;
        this.hasExpiryTime = hasExpiryTime;
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public LocalDate getManufactureDateTime() {
        return manufactureDateTime;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public boolean isHasExpiryTime() {
        return hasExpiryTime;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", manufactureDateTime=" + manufactureDateTime +
                ", manufacturer='" + manufacturer + '\'' +
                ", hasExpiryTime=" + hasExpiryTime +
                ", stock=" + stock +
                '}';
    }
}
