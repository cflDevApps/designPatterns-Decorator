package com.cflDevApps.dpDecorator.contracts.webAuth;

public interface Authenticator {

    public String authenticate(String username, String password);
}
