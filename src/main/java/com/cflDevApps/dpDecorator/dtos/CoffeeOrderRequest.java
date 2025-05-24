package com.cflDevApps.dpDecorator.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeOrderRequest {
    private String coffeeType;
    private String[] options;
}
