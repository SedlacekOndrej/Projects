package com.greenfoxacademy.webshop.models;

public class ShopItem {
    private String name;
    private String description;
    private int price;
    private int quantityOfStock;
    private String type;

    private int priceInEuro;

    public ShopItem(String name, String type, String description, int price, int quantityOfStock) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.quantityOfStock = quantityOfStock;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantityOfStock() {
        return quantityOfStock;
    }

    public String getType() {
        return type;
    }

    public int getPriceInEuro() {
        return price / 25;
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantityOfStock=" + quantityOfStock +
                '}';
    }
}
