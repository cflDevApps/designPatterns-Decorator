package com.cflDevApps.dpDecorator.services.coffee;

import com.cflDevApps.dpDecorator.contracts.Drink;
import com.cflDevApps.dpDecorator.contracts.OptionalDrink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class CoffeeBuilder {

    private Drink baseDrink;

    private Set<String> options = new HashSet<String>();

    private final Map<String, Drink> drinkMap;

    private final Map<String, OptionalDrink> optionalDrinkMap;


    @Autowired
    public CoffeeBuilder(Map<String, Drink> drinkMap, Map<String, OptionalDrink> optionalDrinkMap) {
        this.drinkMap = drinkMap;
        this.optionalDrinkMap = optionalDrinkMap;
    }

    public CoffeeBuilder baseDrink(String drink) {
        this.baseDrink = this.getDrink(drink);
        return this;
    }

    public CoffeeBuilder withOptions(Set<String> options){
       this.options = options;
        return this;
    }


    /**
     * This method sets the base drink for the builder.
     * It retrieves the drink from a map using the provided name.
     *
     * @param drinkName The name of the drink to set as the base.
     * @return The current instance of CoffeeBuilder.
     */
    public Drink build() {
        if (baseDrink == null) {
            throw new IllegalArgumentException("Base drink is not set");
        }

        if(options.isEmpty()){
            return baseDrink;
        }

        Drink finalDrink = baseDrink;

        for(String option : options) {
            try {
                OptionalDrink optionalDrink = this.getOptionalDrink(option);
                finalDrink = optionalDrink.setBaseDrink(finalDrink);
            } catch (IllegalArgumentException iae) {
                log.warn("Optional drink not found: {}", option);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return finalDrink;
    }

    /**
     * This method returns a drink object based on the drink name provided.
     * It uses a map to look up the drink by its name.
     *
     * @param drinkName The name of the drink to retrieve.
     * @return The Drink object associated with the provided name.
     * @throws IllegalArgumentException if the drink is not found in the map.
     */
    public Drink getDrink(String drinkName) {
        Drink drink =  drinkMap.get(drinkName);
        if (drink == null) {
            throw new IllegalArgumentException("Drink not found: " + drinkName);
        }
        return drink;
    }

    /**
     * This method returns an optional drink object based on the drink name provided.
     * It uses a map to look up the drink by its name.
     *
     * @param drinkName The name of the optional drink to retrieve.
     * @return The OptionalDrink object associated with the provided name.
     * @throws IllegalArgumentException if the optional drink is not found in the map.
     */
    public OptionalDrink getOptionalDrink(String drinkName) {
        OptionalDrink drink =  this.optionalDrinkMap.get(drinkName);
        if (drink == null) {
            throw new IllegalArgumentException("Optional drink not found: " + drinkName);
        }
        return drink;
    }
}
