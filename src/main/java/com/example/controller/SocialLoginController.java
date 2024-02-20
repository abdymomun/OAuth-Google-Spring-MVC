package com.maraujo.oauthsocial.controller;

import com.maraujo.oauthsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class SocialLoginController {
    private final UserService userService;
    @Autowired
    public SocialLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model){

        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();

        String name = attributes.getOrDefault("localizedFirstName", attributes.get("given_name")).toString();
        String email = attributes.get("email").toString();
        Object passwordObject = attributes.get("password");
        String password = (passwordObject != null) ? passwordObject.toString() : null;

        userService.registerUser(name,email,password);

        model.addAttribute("name", name);
        model.addAttribute("email", email);
        return "index";
    }
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}
