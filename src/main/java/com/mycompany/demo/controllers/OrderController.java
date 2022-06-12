/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.controllers;

import com.mycompany.demo.entities.Order;
import com.mycompany.demo.entities.Pizza;
import com.mycompany.demo.entities.PizzaUser;
import com.mycompany.demo.mappers.OrderRowMapper;
import com.mycompany.demo.mappers.UserRowMapper;
import com.mycompany.demo.utilitis.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class OrderController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/orders")
    public List<Order> getOrder() {
        String sqlFirst = "SELECT * FROM userPizza WHERE username=?";
        Object[] paramsFirst = { UserUtilities.getLoggedUser()};
        List<PizzaUser> listUsers = jdbcTemplate.query(sqlFirst,paramsFirst, new UserRowMapper());
        List<Order> listOrder = Arrays.asList();

        if (!listUsers.isEmpty()) {
            if (Objects.equals(listUsers.get(0).getUserGroup(), "customer")) {
                String sql = "SELECT * FROM orderPizza WHERE clientid=?";
                Object[] params = {listUsers.get(0).getId()};
                listOrder = jdbcTemplate.query(sql, params, new OrderRowMapper());
            } else if (Objects.equals(listUsers.get(0).getUserGroup(), "employee")) {
                String sql = "SELECT * FROM orderPizza WHERE status!=delivered";
                listOrder = jdbcTemplate.query(sql, new OrderRowMapper());
            }

        }

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
    */

    @PostMapping("/order")
    public Boolean createOrder(@RequestBody List<Integer> pizze, @RequestBody PizzaUser clent) { //(@RequestBody List<Integer>)(@RequestBody Order order)
        //Integer who=0; //tu jakoś id usera(clientID) z sesji
        String sql = "INSERT INTO orderPizza(clientID, status) VALUES (?, 'created')";
        int result = jdbcTemplate.update(sql, clent);

        if (result > 0) {
            for (Integer idPizzy : pizze) {
                String sqlPizzas = "INSERT INTO cartPizza(orderID, menuID) VALUES (?,?)";
                int weakEntity = jdbcTemplate.update(sqlPizzas, clent, idPizzy);
                if (weakEntity > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @PutMapping("/order/{id}")
    public Boolean updateOrder(@PathVariable int id, @RequestBody Order order) {

        String sql = "UPDATE orderPizza SET status =? WHERE orderID=?";
        Object[] params = {order.getStatus(), id};
        int result = jdbcTemplate.update(sql, params);

        if (result > 0) {
            return true;
        }

        return false;
    }


}
