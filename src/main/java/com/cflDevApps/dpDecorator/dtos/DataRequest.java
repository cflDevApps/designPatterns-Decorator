package com.cflDevApps.dpDecorator.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class DataRequest {

    private String data;

    private String actionType;

    private String encryptorType;

    private String compressorType;

    public boolean encryptorTypeIsPresent(){
        return this.encryptorType != null && !this.encryptorType.isEmpty();
    }

    public boolean compressorTypeIsPresent(){
        return this.compressorType != null && !this.compressorType.isEmpty();
    }

}
