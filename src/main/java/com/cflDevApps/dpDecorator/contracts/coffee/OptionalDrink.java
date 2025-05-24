package com.cflDevApps.dpDecorator.contracts.coffee;

/**
 * OptionalDrink interface extends the Drink interface and adds a method to set a base drink.
 * This allows for creating drinks that can have additional options or modifications.
 */

public interface OptionalDrink extends Drink{

    Drink setBaseDrink(Drink drink);
}
