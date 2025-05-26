package com.cflDevApps.dpDecorator.services.webAuth;

import com.cflDevApps.dpDecorator.contracts.webAuth.Authenticator;
import com.cflDevApps.dpDecorator.dtos.User;
import com.cflDevApps.dpDecorator.execeptions.NotAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service("authBase")
public class AuthBase implements Authenticator {

    private final User existingUser = new User("Cristiano", "ronaldo@fake");


    @Override
    public boolean authenticate(User user) {
        if(!user.getUsername().equals(existingUser.getUsername()) ||
        !user.getPassword().equals(existingUser.getPassword())) {
            log.info("Authentication failed, username or password is incorrect.");
            throw new NotAuthorizedException("Username or password is incorrect", HttpStatus.FORBIDDEN);
        }
        return true;
    }
}
