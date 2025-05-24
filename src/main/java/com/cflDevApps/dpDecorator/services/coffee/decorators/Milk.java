package com.cflDevApps.dpDecorator.services.coffee.decorators;

import com.cflDevApps.dpDecorator.contracts.coffee.Drink;
import com.cflDevApps.dpDecorator.contracts.coffee.OptionalDrink;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Service("Milk")
public class Milk implements OptionalDrink {

    private Drink baseDrink;

    @Override
    public String getDescription() {
        return String.format("%s + Milk",baseDrink.getDescription());
    }

    @Override
    public Float getPrice() {
        Float basePrice = baseDrink.getPrice();
        return basePrice + .50f;
    }

    @Override
    public Milk setBaseDrink(Drink drink) {
        this.baseDrink = drink;
        return this;
    }
}
