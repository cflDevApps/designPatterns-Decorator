package com.cflDevApps.dpDecorator.services.coffee;

import com.cflDevApps.dpDecorator.contracts.Drink;
import org.springframework.stereotype.Service;

@Service("BlackTea")
public class Tea implements Drink {
    @Override
    public String getDescription() {
        return "Black Tea";
    }

    @Override
    public Float getPrice() {
        return 1.30f;
    }
}
