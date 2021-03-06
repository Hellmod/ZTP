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
    public PizzaUser sayAuth() {
        String sqlFirst = "SELECT * FROM userPizza WHERE username=?";
        Object[] params = { UserUtilities.getLoggedUser()};
        List<PizzaUser> listUsers = jdbcTemplate.query(sqlFirst,params, new UserRowMapper());


        if(listUsers==null|| listUsers.isEmpty()){
            return new PizzaUser();
        }
        PizzaUser response = listUsers.get(0);
        response.setPassword("");

        return response;
    }

}
