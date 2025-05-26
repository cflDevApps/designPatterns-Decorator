package com.cflDevApps.dpDecorator.controlers;

import com.cflDevApps.dpDecorator.contracts.coffee.Drink;
import com.cflDevApps.dpDecorator.dtos.CoffeeOrderRequest;
import com.cflDevApps.dpDecorator.services.coffee.CoffeeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Set;

/**
 * Client of Decorator Pattern
 */
@Controller
public class CoffeeController {

    @Autowired
    private CoffeeBuilder coffeeBuilder;

    @GetMapping("/coffee")
    public String home(Model model) {
        model.addAttribute("title", "Monte seu o cafe");

        return "coffee-form";
    }

    @PostMapping("/coffee/order")
    public String home(Model model, CoffeeOrderRequest request) {
        Drink finalDrink = coffeeBuilder.baseDrink(request.getCoffeeType()).withOptions(Set.of(request.getOptions())).build();
        model.addAttribute("title", "Drink ordered:");
        model.addAttribute("drinkDescription", String.format("%s", finalDrink.getDescription()));
        model.addAttribute("drinkPrice", String.format("Total to pay: $%.2f", finalDrink.getPrice()));
        return "coffee-order";
    }



}
