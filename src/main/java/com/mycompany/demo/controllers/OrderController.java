/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.controllers;

import com.mycompany.demo.entities.*;
import com.mycompany.demo.mappers.CartRowMapper;
import com.mycompany.demo.mappers.OrderRowMapper;
import com.mycompany.demo.mappers.PizzaRowMapper;
import com.mycompany.demo.mappers.UserRowMapper;
import com.mycompany.demo.utilitis.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class OrderController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/orders")
    public List<OrderWithPizzas> getOrder() {
        String sqlFirst = "SELECT * FROM userPizza WHERE username=?";
        Object[] paramsFirst = {UserUtilities.getLoggedUser()};
        List<PizzaUser> listUsers = jdbcTemplate.query(sqlFirst, paramsFirst, new UserRowMapper());
        List<Order> listOrder = new ArrayList<>();
        List<Cart> listCart = new ArrayList<>();
        List<OrderWithPizzas> response = new ArrayList<>();


        if (!listUsers.isEmpty()) {
            if (Objects.equals(listUsers.get(0).getUserGroup(), "customer")) {
                String sql = "SELECT * FROM orderPizza WHERE clientid=?";
                Object[] params = {listUsers.get(0).getId()};
                listOrder = jdbcTemplate.query(sql, params, new OrderRowMapper());

                generateResponce(listOrder, response);

            } else if (Objects.equals(listUsers.get(0).getUserGroup(), "employee")) {
                String sql = "SELECT * FROM orderPizza WHERE status!='delivered'";
                listOrder = jdbcTemplate.query(sql, new OrderRowMapper());

                generateResponce(listOrder, response);

            } else if (Objects.equals(listUsers.get(0).getUserGroup(), "admin")) {
                String sql = "SELECT * FROM orderPizza";
                listOrder = jdbcTemplate.query(sql, new OrderRowMapper());

                generateResponce(listOrder, response);
            }

        }

        for (Order order : listOrder) {
            System.out.println(order);
        }

        return response;
    }

    private void generateResponce(List<Order> listOrder, List<OrderWithPizzas> response) {
        List<Cart> listCart;
        for (Order order : listOrder) {
            List<Integer> pizzaIdList = new ArrayList<>();

            String sqlFirst = "SELECT * FROM userPizza WHERE userid=?";
            Object[] params = {order.getIdClient()};
            List<PizzaUser> listUsers = jdbcTemplate.query(sqlFirst,params, new UserRowMapper());

            String sqlBLA = "SELECT * FROM cartPizza WHERE orderid = ?";
            Object[] paramsBLA = {order.getId()};
            listCart = jdbcTemplate.query(sqlBLA, paramsBLA, new CartRowMapper());

            for (Cart cart : listCart) {
                pizzaIdList.add(cart.getMenuid());
            }

            String fullname = listUsers.get(0).getFullName();
            response.add(
                    new OrderWithPizzas(
                            order.getId(),
                            order.getIdClient(),
                            order.getStatus(),
                            pizzaIdList,
                            fullname
                    )
            );
        }
    }

    @PostMapping("/order")
    public Boolean createOrder(@RequestBody List<Integer> pizze) {

        String sqlFirst = "SELECT * FROM userPizza WHERE username=?";
        Object[] paramsFirst = {UserUtilities.getLoggedUser()};
        List<PizzaUser> listUsers = jdbcTemplate.query(sqlFirst, paramsFirst, new UserRowMapper());


        //Integer who=0; //tu jakoś id usera(clientID) z sesji
        String sql = "INSERT INTO orderPizza(clientID, status) VALUES (?, 'created')";
        int result = jdbcTemplate.update(sql, listUsers.get(0).getId());

        String sqlSec = "SELECT * FROM orderPizza WHERE orderid=(SELECT max(orderid) FROM orderPizza)";
        List<Order> newId = jdbcTemplate.query(sqlSec, new OrderRowMapper());

        if (result > 0) {
            int weakEntity = -1;
            for (Integer idPizzy : pizze) {
                String sqlPizzas = "INSERT INTO cartPizza(orderID, menuID) VALUES (?,?)";
                weakEntity = jdbcTemplate.update(sqlPizzas, newId.get(0).getId(), idPizzy);
            }
            if (weakEntity > 0) {
                return true;
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
