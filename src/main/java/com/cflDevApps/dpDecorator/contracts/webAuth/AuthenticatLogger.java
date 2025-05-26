package com.cflDevApps.dpDecorator.contracts.webAuth;

public interface AuthenticatLogger extends Authenticator{

    void logAuthentication(String username, boolean success);

}
