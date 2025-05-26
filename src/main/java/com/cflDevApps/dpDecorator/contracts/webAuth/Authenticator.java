package com.cflDevApps.dpDecorator.contracts.webAuth;

import com.cflDevApps.dpDecorator.dtos.User;

public interface Authenticator {

    public boolean authenticate(User user);
}
