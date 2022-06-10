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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


    public DashboardController() {
        books.add(new Book("tytuł1", "autor1", 1991));
        books.add(new Book("tytuł2", "autor2", 1992));
        books.add(new Book("tytuł3", "autor3", 1993));
        books.add(new Book("tytuł4", "autor4", 1994));
        books.add(new Book("tytuł5", "autor5", 1995));
        books.add(new Book("tytuł6", "autor6", 1996));
        books.add(new Book("tytuł7", "autor7", 1997));
        books.add(new Book("tytuł8", "autor8", 1998));
    }

    @GetMapping("/dashboard/{id}")
    public Book getBook(@PathVariable int id) {
        return dashboardService.getBook(id);
    }


    @GetMapping("/dashboard")
    public ArrayList<Book> getBooks() {
        return dashboardService.getBooks();
    }

    @PostMapping("/dashboard")
    public ArrayList<Book> createBook(@RequestBody Book book) {
        dashboardService.createBook(book.getTitle(), book.getAuthor(), book.getYear());
        return dashboardService.getBooks();
    }

    @PutMapping("/dashboard/{id}")
    public ArrayList<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        dashboardService.updateBook(id, book.getTitle(), book.getAuthor(), book.getYear());
        return dashboardService.getBooks();
    }

    @DeleteMapping("/dashboard/{id}")
    public Book removeBook(@PathVariable int id) {
        Book book = books.get(id);
        dashboardService.removeBook(id);
        return book;
    }

}
