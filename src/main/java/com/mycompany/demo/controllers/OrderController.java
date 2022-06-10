/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.controllers;

import com.mycompany.demo.entities.Order;
import com.mycompany.demo.entities.Pizza;
import com.mycompany.demo.mappers.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/order")
    public List<Order> getPizzas() {
        String sql = "SELECT * FROM orderPizza";

        List<Order> listOrder = jdbcTemplate.query(
                sql,
                new OrderRowMapper());

        for (Order order : listOrder) {
            System.out.println(order);
        }

        return listOrder;
    }
    /*

    @GetMapping("/shoppingCart")
    public List<Order> getShoppingCart() {
        String sql = "SELECT * FROM menuPizza WHERE menuID = 1 ";

        List<Order> listOrder = jdbcTemplate.query(
                sql,
                new OrderRowMapper());

        for (Order order : listOrder) {
            System.out.println(order);
        }

        return listOrder;
    }

    @PostMapping("/pizza")
    public Boolean createPizza(@RequestBody Pizza pizza) {
        String sql = "INSERT INTO menuPizza ( name, ingredients, price) VALUES (?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, pizza.getName(), pizza.getIngredients(), pizza.getPrice());

        if (result > 0) {
            return true;
        }

        return false;
    }

    @PutMapping("/pizza/{id}")
    public Boolean updatePizza(@PathVariable int id, @RequestBody Pizza pizza) {

        String sql = "UPDATE menuPizza SET name=?, ingredients=?, price=? WHERE menuID =?";
        Object[] params = {pizza.getName(), pizza.getIngredients(), pizza.getPrice(), id};
        int result = jdbcTemplate.update(sql, params);

        if (result > 0) {
            return true;
        }

        return false;
    }

    @DeleteMapping("/pizza/{id}")
    public Boolean removePizza(@PathVariable int id) {

        String sql = "DELETE FROM menuPizza WHERE menuID=?";
        Object[] params = {id};
        int result = jdbcTemplate.update(sql, params);

        if (result > 0) {
            return true;
        }

        return false;
    }
    */
}
