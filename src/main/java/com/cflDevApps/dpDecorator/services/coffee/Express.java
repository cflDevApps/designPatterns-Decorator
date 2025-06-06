package com.cflDevApps.dpDecorator.services.coffee;

import com.cflDevApps.dpDecorator.contracts.coffee.Drink;
import org.springframework.stereotype.Service;

@Service("Express")
public class Express implements Drink {
    @Override
    public String getDescription() {
        return "Express coffee";
    }

    @Override
    public Float getPrice() {
        return 2.5f;
    }
}
