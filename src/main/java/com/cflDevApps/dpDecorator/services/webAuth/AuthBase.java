package com.cflDevApps.dpDecorator.services.webAuth;

import com.cflDevApps.dpDecorator.contracts.webAuth.Authenticator;
import org.springframework.stereotype.Service;

@Service("authBase")
public class AuthBase implements Authenticator {


    @Override
    public String authenticate(String username, String password) {
        return String.format("User %s authenticated", username);
    }
}
