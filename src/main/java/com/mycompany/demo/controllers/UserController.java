/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.controllers;

import com.mycompany.demo.entities.Book;
import com.mycompany.demo.entities.Pizza;
import com.mycompany.demo.entities.PizzaUser;
import com.mycompany.demo.entities.User;
import com.mycompany.demo.mappers.PizzaRowMapper;
import com.mycompany.demo.mappers.UserRowMapper;
import com.mycompany.demo.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/users")
    public List<PizzaUser> getUsers() {
        String sql = "SELECT * FROM userPizza";

        List<PizzaUser> listUsers = jdbcTemplate.query(
                sql,
                new UserRowMapper());

        for (PizzaUser user : listUsers) {
            System.out.println(user);
        }

        return listUsers;
    }

    @PostMapping("/login")
    public Boolean loginUser(@RequestBody PizzaUser user) {

        String sql = "SELECT * FROM userPizza WHERE username=? AND password=?";
        int result = jdbcTemplate.update(sql, user.getUsername(), user.getPassword());

        if (result > 0) {
            return true;
        }
        return false;
    }

    @PostMapping("/register")
    public Boolean createUser(@RequestBody PizzaUser user) {

        String sqltest = "SELECT * FROM userPizza WHERE username=?";
        int test = jdbcTemplate.update(sqltest, user.getUsername());

        if(test<=0) {

            String sql = "INSERT INTO userPizza(username, password, fullName, roleUser) VALUES (?, ?, ?, 'CUSTOMER')";
            int result = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getFullName());

            if (result > 0) {
                return true;
            }
        }
            return false;
    }

    @PutMapping("/user/{id}")
    public Boolean updateUser(@PathVariable int id, @RequestBody PizzaUser user) {

        String sql = "UPDATE userPizza SET username = ?, fullName = ?, roleUser =? WHERE userID =?";
        Object[] params = {user.getUsername(), user.getFullName(), user.getUserGroup(), id};
        int result = jdbcTemplate.update(sql, params);

        if (result > 0) {
            return true;
        }

        return false;
    }

    @DeleteMapping("/user/{id}")
    public Boolean removeUser(@PathVariable int id) {

        String sql = "DELETE FROM userPizza WHERE userPizza.userID = ?";
        Object[] params = {id};
        int result = jdbcTemplate.update(sql, params);

        if (result > 0) {
            return true;
        }

        return false;
    }

}
