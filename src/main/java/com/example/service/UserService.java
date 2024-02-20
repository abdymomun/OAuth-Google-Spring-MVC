package com.maraujo.oauthsocial.service;

import com.maraujo.oauthsocial.db.entities.User;
import com.maraujo.oauthsocial.db.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void registerUser(String userName, String email,String password) {
        User user = new User();
        user.setFirstName(userName);

        user.setEmail(email);
        user.setPassword(password);


        userRepository.save(user);
    }
    public List<String> getAllUserNames() {
        List<String> userNames = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            userNames.add(user.getFirstName());
        });
        return userNames;
    }



}
