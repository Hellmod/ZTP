/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.services;

import java.util.HashMap;
import java.util.List;

import com.mycompany.demo.entities.PizzaUser;
import com.mycompany.demo.entities.User;
import com.mycompany.demo.enums.Role;

import com.mycompany.demo.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    // HashMap<String, User> users = new HashMap<String, User>();

/*    public UserService() {
        users.put("admin", new User("admin", "admin", Role.ADMIN));
        users.put("1", new User("1", "1"));
        users.put("2", new User("2", "2"));
        users.put("3", new User("3", "3"));
        users.put("4", new User("4", "4"));
        users.put("5", new User("5", "5"));
    }*/

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserByLogin(String login) {
        String sqlFirst = "SELECT * FROM userPizza WHERE username=?";
        Object[] params = {login};
        List<PizzaUser> listUsers = jdbcTemplate.query(sqlFirst, params, new UserRowMapper());
        Role userRole = Role.CUSTOMER;

        switch (listUsers.get(0).getUserGroup()) {
            case "admin":
                userRole = Role.ADMIN;
                break;
            case "customer":
                userRole = Role.CUSTOMER;
                break;
            case "employee":
                userRole = Role.EMPLOYEE;
                break;
        }


        User user = new User(
                listUsers.get(0).getUsername(),
                listUsers.get(0).getPassword(),
                userRole
        );
        return user;
    }

}
