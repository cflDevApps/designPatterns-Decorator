package com.cflDevApps.dpDecorator.services.webAuth.decorators;

import com.cflDevApps.dpDecorator.contracts.webAuth.AuthenticatLogger;
import com.cflDevApps.dpDecorator.contracts.webAuth.Authenticator;
import com.cflDevApps.dpDecorator.dtos.User;
import com.cflDevApps.dpDecorator.execeptions.NotAuthorizedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthWithLogger implements AuthenticatLogger {

    private final Authenticator authBase;

    public AuthWithLogger(Authenticator authBase) {
        this.authBase = authBase;
    }

    @Override
    public void logAuthentication(String username, boolean success) {
        log.warn("Logging authentication for user: {}, success: {}", username, success);
    }

    @Override
    public boolean authenticate(User user) {
        boolean isAuthenticated = false;
        try {
            isAuthenticated = authBase.authenticate(user);
            return isAuthenticated;
        }catch (Exception e) {
            throw e; // rethrow the exception after logging
        } finally {
            logAuthentication(user.getUsername(), isAuthenticated);
        }
    }
}
