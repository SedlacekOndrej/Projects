package com.greenfoxacademy.webshop.controllers;

import com.greenfoxacademy.webshop.services.ShopList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String getTableOfItems(Model model) {
        model.addAttribute("list", ShopList.getListOfItems());
        return "index";
    }

    @GetMapping("/only-available")
    public String getOnlyAvailableItems(Model model) {
        model.addAttribute("list", ShopList.onlyAvailable());
        return "index";
    }

    @GetMapping("/cheapest-first")
    public String getItemsSortedByPrice(Model model) {
        model.addAttribute("list", ShopList.cheapestFirst());
        return "index";
    }

    @GetMapping("/contains-cars")
    public String getCarItems(Model model) {
        model.addAttribute("list", ShopList.containsCar());
        return "index";
    }

    @GetMapping("/average-stock")
    public String getAverageStock(Model model) {
        model.addAttribute("average", ShopList.averageStock());
        return "averageStock";
    }

    @GetMapping("/most-expensive-available")
    public String getMostExpensiveAvailableItem(Model model) {
        model.addAttribute("list", ShopList.mostExpensiveAvailable());
        return "index";
    }

    @PostMapping( "/search")
    public String showMeSpecifiedItem(@RequestParam(required = false, defaultValue = "", name = "item") String item, Model model) {
        model.addAttribute("list", ShopList.getListOfItems().stream().filter(n -> n.getDescription().toLowerCase().contains(item.toLowerCase()) || n.getName().toLowerCase().contains(item.toLowerCase())).collect(Collectors.toList()));
        model.addAttribute("query", item);
        return "index";
    }

    @GetMapping("/more-options")
    public String showMeMoreOptions(Model model) {
        model.addAttribute("list", ShopList.getListOfItems());
        return "more-options";
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("list", ShopList.showMeCars());
        return "more-options";
    }

    @GetMapping("/mobile-phones")
    public String getMobilePhones(Model model) {
        model.addAttribute("list", ShopList.showMeMobilePhones());
        return "more-options";
    }

    @GetMapping("/others")
    public String getOtherStuff(Model model) {
        model.addAttribute("list", ShopList.showMeOtherStuff());
        return "more-options";
    }

    @GetMapping("/original-currency")
    public String getOriginalCurrency(Model model) {
        model.addAttribute("list", ShopList.getListOfItems());
        return "more-options";
    }

    @GetMapping("/euro-currency")
    public String getEuroCurrency(Model model) {
        model.addAttribute("list", ShopList.getListOfItems());
        return "euro-more-options";
    }

    @PostMapping("/price-filter")
    public String filterByPrice(@RequestParam("filterType") ShopList.priceFilterOption filterType, @RequestParam("number") int price, Model model) {
        model.addAttribute("list", ShopList.filterItemsByPrice(filterType, price));
        model.addAttribute("number", price);
        return "more-options";
    }

}
