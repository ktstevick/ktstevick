package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserService {
    private static final String API_BASE_URL = "http://localhost:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    public User getUserById(int id) {
        User user = null;
        try {
            user = restTemplate.getForObject(API_BASE_URL + "users/" + id, User.class);
        } catch(RestClientResponseException e) {
            // :D
        }
        return user;
    }

    public User[] listUsers() {
        User[] users = null;
        try {
            users = restTemplate.getForObject(API_BASE_URL + "users", User[].class);
        } catch(RestClientException e) {
            System.out.println("Something bad happened!");
        }

        for (User user: users){
            String username = user.getUsername();
            int userid = user.getId();

            System.out.println(userid +"        " + username);
        }

        return users;
    }
}
