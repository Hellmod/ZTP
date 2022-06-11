package com.mycompany.demo.controllers;

import com.mycompany.demo.entities.PizzaUser;
import com.mycompany.demo.mappers.UserRowMapper;
import com.mycompany.demo.utilitis.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Hello {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/role")
    public String sayAuth() {
        UserUtilities.getLoggedUser();

        String sql = "SELECT * FROM userPizza WHERE username=?";
        List<PizzaUser> listUsers = jdbcTemplate.query(sql, new UserRowMapper());

        if(listUsers==null|| listUsers.isEmpty()){
            return "Guest";
        }

        return listUsers.get(0).getUserGroup();
    }

}
