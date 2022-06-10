package com.mycompany.demo.entities;

import com.mycompany.demo.enums.OrderStatus;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int idClient;
    private String status;
    //private OrderStatus status;
    private String OrderTime;
    //private Timestamp OrderTime;

    protected Order(int id, int idClient, String status, String OrderTime) {
        this.id = id;
        this.idClient = idClient;
        this.status = status;
        this.OrderTime = OrderTime;
    }

    @Override
    public String toString() {
        return "Order [" + id + ", " + idClient + ", " + status + ", " + OrderTime + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        this.OrderTime = orderTime;
    }

    public Order() {
    }

    public void getOrderTime(String orderTime) {
    }


    // getters and setters...
}
