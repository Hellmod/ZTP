package com.mycompany.demo.mappers;

import com.mycompany.demo.entities.Cart;
import com.mycompany.demo.entities.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowMapper implements RowMapper<Cart> {

    @Override
    public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {

        Cart cart = new Cart();

        cart.setOrderid(rs.getInt("orderID"));
        cart.setMenuid(rs.getInt("menuID"));

        return cart;

    }
}