package com.cflDevApps.dpDecorator.services.webAuth;

import com.cflDevApps.dpDecorator.contracts.webAuth.Authenticator;
import com.cflDevApps.dpDecorator.dtos.User;
import com.cflDevApps.dpDecorator.execeptions.MaxAttemptsExceededException;
import com.cflDevApps.dpDecorator.execeptions.NotAuthorizedException;
import com.cflDevApps.dpDecorator.services.webAuth.decorators.Auth2Facs;
import com.cflDevApps.dpDecorator.services.webAuth.decorators.AuthWithLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.cflDevApps.dpDecorator.configs.CacheConfig.LONGIN_COUNTER;



@Service
@Slf4j
public class LoginService {

    private static final Integer MAX_ATTEMPTS = 3;

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private CacheManager cacheManager;

    public void doLogin(User user) {
        Cache loginCache = cacheManager.getCache(LONGIN_COUNTER);
        int attemptsCounter = Optional.ofNullable(loginCache.get(user.getUsername(), Integer.class)).orElse(0);

        try {
            if (attemptsCounter > 2) {
                throw new MaxAttemptsExceededException();
            }


            if (attemptsCounter < 1) {
                authenticator.authenticate(user);
            }

            if (attemptsCounter > 0) {
                AuthWithLogger authWithLogger = new AuthWithLogger(authenticator);

                if (attemptsCounter == 2) {
                    new Auth2Facs(authWithLogger).authenticate(user);

                } else {
                    authWithLogger.authenticate(user);
                }

            }

            loginCache.clear();
        } catch (NotAuthorizedException nae) {
            loginCache.put(user.getUsername(), ++attemptsCounter);
            throw new NotAuthorizedException(
                    String.format(
                            "%s : Tentativas %d / 3", nae.getMessage(),MAX_ATTEMPTS -(MAX_ATTEMPTS - attemptsCounter)),
                            nae.getStatus());
        }
    }
}
