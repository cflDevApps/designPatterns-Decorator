package com.cflDevApps.dpDecorator.controlers;

import com.cflDevApps.dpDecorator.contracts.webAuth.Authenticator;
import com.cflDevApps.dpDecorator.dtos.User;
import com.cflDevApps.dpDecorator.execeptions.MaxAttemptsExceededException;
import com.cflDevApps.dpDecorator.execeptions.NotAuthorizedException;
import com.cflDevApps.dpDecorator.services.webAuth.LoginService;
import com.cflDevApps.dpDecorator.services.webAuth.decorators.Auth2Facs;
import com.cflDevApps.dpDecorator.services.webAuth.decorators.AuthWithLogger;
import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import static com.cflDevApps.dpDecorator.configs.CacheConfig.LONGIN_COUNTER;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login(Model model, User user) {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(Model model, User user) {
        try{
            loginService.doLogin(user);
            return "redirect:/home"; // Redirect to coffee form if authentication is successful
        }catch (NotAuthorizedException | MaxAttemptsExceededException ex){
            model.addAttribute("error", ex.getMessage());
        }
        return "login";
    }


    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }

}
