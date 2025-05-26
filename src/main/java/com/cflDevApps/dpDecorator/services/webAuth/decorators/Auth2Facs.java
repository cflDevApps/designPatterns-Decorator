package com.cflDevApps.dpDecorator.services.webAuth.decorators;

import com.cflDevApps.dpDecorator.contracts.webAuth.Authentication2Factories;
import com.cflDevApps.dpDecorator.contracts.webAuth.Authenticator;
import com.cflDevApps.dpDecorator.dtos.User;
import com.cflDevApps.dpDecorator.execeptions.MaxAttemptsExceededException;
import com.cflDevApps.dpDecorator.execeptions.NotAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


@Slf4j
public class Auth2Facs implements Authentication2Factories {

    private final Authenticator baseAuthenticator;

    public Auth2Facs(Authenticator baseAuthenticator) {
        this.baseAuthenticator = baseAuthenticator;
    }

    @Override
    public void authenticate2factories() {
        log.warn("Authenticating with two-factor authentication...");
        log.warn("Two-factor authentication successful!");
    }

    @Override
    public boolean authenticate(User user) {
        try {
            // Call the base authenticator's authenticate method
            boolean isAuthenticated = baseAuthenticator.authenticate(user);
            if (isAuthenticated) {
               authenticate2factories();
            }
            return isAuthenticated;
        }catch (NotAuthorizedException nae){
            log.warn("Authentication failed 2Facts not Executed: {}", nae.getMessage());
            throw new MaxAttemptsExceededException();
        }catch (Exception e) {
            // Handle any exceptions that may occur during authentication
            log.error("Authentication failed: {}", e.getMessage());
            throw e; // Re-throw the exception to indicate failure
        }
    }
}
