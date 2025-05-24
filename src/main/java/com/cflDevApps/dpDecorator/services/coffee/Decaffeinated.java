package com.cflDevApps.dpDecorator.services.coffee;

import com.cflDevApps.dpDecorator.contracts.Drink;
import org.springframework.stereotype.Service;

@Service("Decaffeinated")
public class Decaffeinated implements Drink {
    @Override
    public String getDescription() {
        return "Decaf coffee";
    }

    @Override
    public Float getPrice() {
        return 1.5f;
    }
}
