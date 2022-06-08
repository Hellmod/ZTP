package com.mycompany.demo.mappers;

import org.springframework.jdbc.core.RowMapper;
import com.mycompany.demo.entities.Pizza;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PizzaRowMapper implements RowMapper<Pizza> {

    @Override
    public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {

        Pizza pizza = new Pizza();

        pizza.setId(rs.getInt("id"));
        pizza.setName(rs.getString("name"));
        pizza.setIngredients(rs.getString("ingredients"));
        pizza.setPrice(rs.getInt("price"));

        return pizza;

    }
}