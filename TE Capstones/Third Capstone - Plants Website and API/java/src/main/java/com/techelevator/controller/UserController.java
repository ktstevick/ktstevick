package com.techelevator.controller;


import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController

public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path ="/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path ="/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        } else {
            return userDao.getUserById(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path ="/user/{name}", method = RequestMethod.GET)
    public User getUserById(@PathVariable String username) {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
        } else {
            return userDao.getUserByUsername(username);
        }
    }
}
