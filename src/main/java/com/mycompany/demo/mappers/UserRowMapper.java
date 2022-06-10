package com.mycompany.demo.mappers;

import com.mycompany.demo.entities.PizzaUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<PizzaUser> {

    @Override
    public PizzaUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        PizzaUser pizza = new PizzaUser();

        pizza.setId(rs.getInt("userID"));
        pizza.setUsername(rs.getString("username"));
        pizza.setPassword(rs.getString("password"));
        pizza.setFullName(rs.getString("fullName"));
        pizza.setRoleUser(rs.getString("roleUser"));

        return pizza;

    }
}