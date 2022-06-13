/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.controllers;

import com.mycompany.demo.entities.Book;
import com.mycompany.demo.entities.Pizza;
import com.mycompany.demo.mappers.PizzaRowMapper;
import com.mycompany.demo.services.DashboardService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    ArrayList<Book> books = new ArrayList<>();


    @GetMapping("/pizza")
    public List<Pizza> getPizzas() {
        String sql = "SELECT * FROM menuPizza";

        List<Pizza> listPizza = jdbcTemplate.query(
                sql,
                new PizzaRowMapper());

        for (Pizza pizza : listPizza) {
            System.out.println(pizza);
        }

        return listPizza;
    }

    @GetMapping("/shoppingCart")
    public List<Pizza> getShoppingCart() {
        String sql = "SELECT * FROM menuPizza WHERE menuID = 1 ";

        List<Pizza> listPizza = jdbcTemplate.query(
                sql,
                new PizzaRowMapper());

        for (Pizza pizza : listPizza) {
            System.out.println(pizza);
        }

        return listPizza;
    }


    @RequestMapping(value = "/foo", method = RequestMethod.OPTIONS)
    public ResponseEntity options(HttpServletResponse response) {

        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
        return new ResponseEntity(HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/pizza")
    public ResponseEntity createPizza(HttpServletResponse response,@RequestBody Pizza pizza) {
        response.setHeader("Allow", "HEAD,GET,PUT,OPTIONS");
        String sql = "INSERT INTO menuPizza ( name, ingredients, price) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, pizza.getName(), pizza.getIngredients(), pizza.getPrice());

        if (result > 0) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
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

}
