package com.greenfoxacademy.webshop.services;

import com.greenfoxacademy.webshop.models.ShopItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ShopList {
    private static List<ShopItem> listOfItems = new ArrayList<>(Arrays.asList(
            new ShopItem("Playstation 5", "others", "Sony product to play video games", 13890, 0),
            new ShopItem("Honor 50 Pro", "mobile phone", "modern phone with many possibilities to do", 10990, 18),
            new ShopItem("Nokia 3310", "mobile phone", "old phone from the previous century", 890, 11),
            new ShopItem("Mazda 6", "car", "beautiful new car of blue color", 929900, 4),
            new ShopItem("Tesla ElonX", "car", "future car, not finished yet", 2929900, 0),
            new ShopItem("Lego car", "others", "for kids being 3+ years old", 199, 27),
            new ShopItem("Fiat Multipla", "car", "weird-looking car suitable for big family", 319900, 36)
    ));

    public static List<ShopItem> getListOfItems() {
        return listOfItems;
    }

    public static List<ShopItem> onlyAvailable() {
        return listOfItems.stream().filter(n -> n.getQuantityOfStock() > 0).collect(Collectors.toList());
    }

    public static List<ShopItem> cheapestFirst() {
        return listOfItems.stream().sorted(Comparator.comparingInt(ShopItem::getPrice))
                .collect(Collectors.toList());
    }

    public static List<ShopItem> containsCar() {
        return listOfItems.stream()
                .filter(n -> n.getDescription().toLowerCase().contains("car") || n.getName().toLowerCase().contains("car"))
                .collect(Collectors.toList());
    }

    public static double averageStock() {
        return listOfItems.stream().mapToDouble(ShopItem::getQuantityOfStock).average().getAsDouble();
    }

    public static List<ShopItem> mostExpensiveAvailable() {
        return listOfItems.stream().filter(n -> n.getQuantityOfStock() > 0).max(Comparator.comparing(ShopItem::getPrice)).stream().toList();
    }

    public static List<ShopItem> showMeCars() {
        return listOfItems.stream().filter(n -> n.getType().toLowerCase().contains("car")).collect(Collectors.toList());
    }

    public static List<ShopItem> showMeMobilePhones() {
        return listOfItems.stream().filter(n -> n.getType().toLowerCase().contains("mobile phone")).collect(Collectors.toList());
    }

    public static List<ShopItem> showMeOtherStuff() {
        return listOfItems.stream().filter(n -> n.getType().toLowerCase().contains("others")).collect(Collectors.toList());
    }

    public enum priceFilterOption {
        Above, Below, Exactly
    }

    public static List<ShopItem> filterItemsByPrice (priceFilterOption filterOption, int price) {
        Predicate<ShopItem> filter;
        switch (filterOption) {
            case Above -> filter = item -> item.getPrice() > price;
            case Below -> filter = item -> item.getPrice() < price;
            case Exactly -> filter = item -> item.getPrice() == price;
            default -> throw new IllegalArgumentException("Unknown price filter option value has been provided.");
        }
        return getListOfItems().stream().filter(filter).collect(Collectors.toList());
    }

}
