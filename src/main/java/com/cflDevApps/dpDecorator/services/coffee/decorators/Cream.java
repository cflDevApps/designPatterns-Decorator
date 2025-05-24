package com.cflDevApps.dpDecorator.services.coffee.decorators;

import com.cflDevApps.dpDecorator.contracts.Drink;
import com.cflDevApps.dpDecorator.contracts.OptionalDrink;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service("Cream")
public class Cream implements OptionalDrink {

    private Drink baseDrink;

    @Override
    public Drink setBaseDrink(Drink drink) {
        this.baseDrink = drink;
        return this;
    }

    @Override
    public String getDescription() {
        return String.format("%s + Cream", baseDrink.getDescription());
    }

    @Override
    public Float getPrice() {
        Float basePrice = baseDrink.getPrice();
        return basePrice + .75f;
    }
}
