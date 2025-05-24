package com.cflDevApps.dpDecorator.controlers;

import com.cflDevApps.dpDecorator.contracts.Drink;
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
public class InitController {

    @Autowired
    private CoffeeBuilder coffeeBuilder;

    @GetMapping("/frete/{type}/{peso}")
    public String home(Model model,
                       @PathVariable("type") String freteType,
                       @PathVariable("peso") String peso) {
        model.addAttribute("title", String.format("Calculadora de Frete Iniciado: %s", freteType));


        model.addAttribute("price", String.format("Preço: R$ %s", CURRENCY_FORMATTER(new BigDecimal(0))));
        model.addAttribute("term", String.format("Prazo de entrega: %s", "0"));

        return "index";
    }

    @PostMapping("/coffee/order")
    public String home(Model model, CoffeeOrderRequest request) {


        Drink finalDrink = coffeeBuilder.baseDrink(request.getCoffeeType()).withOptions(Set.of(request.getOptions())).build();


        model.addAttribute("title", "Drink ordered:");
        model.addAttribute("drinkDescription", String.format("%s", finalDrink.getDescription()));
        model.addAttribute("drinkPrice", String.format("Total to pay: $%.2f", finalDrink.getPrice()));


        return "coffee-order";
    }

    @GetMapping("/coffee")
    public String home(Model model) {
        model.addAttribute("title", "Monte seu o cafe");

        return "coffee-form";
    }


    private static String CURRENCY_FORMATTER(BigDecimal value) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        return df.format(value);
    }


}
