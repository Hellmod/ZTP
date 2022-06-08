/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.services;

import java.util.HashMap;

import com.mycompany.demo.entities.User;
import com.mycompany.demo.enums.Role;

import org.springframework.stereotype.Service;

/**
 *
 * @author pawel
 */
@Service
public class UserService {
    HashMap<String, User> users = new HashMap<String, User>();

    public UserService() {
        users.put("admin", new User("admin", "admin", Role.ADMIN));
        users.put("1", new User("1", "1"));
        users.put("2", new User("2", "2"));
        users.put("3", new User("3", "3"));
        users.put("4", new User("4", "4"));
        users.put("5", new User("5", "5"));
    }

    public User getUserByLogin(String login) {
        User user = users.get(login);
        return user;
    }

}
