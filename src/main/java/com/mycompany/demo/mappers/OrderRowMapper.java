package com.mycompany.demo.mappers;

import com.mycompany.demo.entities.Order;
import com.mycompany.demo.entities.Pizza;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

        Order order = new Order();

        order.setId(rs.getInt("orderID"));
        order.setIdClient(rs.getInt("clientID"));
        order.setStatus(rs.getString("status"));

        return order;

    }
}