package com.cflDevApps.dpDecorator.contracts;


/** * Interface representing a drink.
 * <p>
 * This interface defines the contract for any drink, requiring the implementation
 * of methods to get the description and price of the drink.
 */
public interface Drink {

    public String getDescription();

    public Float getPrice();

}
