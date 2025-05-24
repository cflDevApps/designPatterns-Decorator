package com.cflDevApps.dpDecorator.services.coffee;

import com.cflDevApps.dpDecorator.contracts.Drink;
import org.springframework.stereotype.Service;

@Service("Black")
public class Black implements Drink {
    @Override
    public String getDescription() {
        return "Black coffee";
    }

    @Override
    public Float getPrice() {
        return 2f;
    }
}
