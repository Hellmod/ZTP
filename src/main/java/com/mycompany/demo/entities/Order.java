package com.mycompany.demo.entities;

import com.mycompany.demo.enums.OrderStatus;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int idClient;
    private String status;
    //private String OrderTime;
    //private Timestamp OrderTime;

    protected Order(int id, int idClient, String status) {
        this.id = id;
        this.idClient = idClient;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [" + id + ", " + idClient + ", " + status + ", " + "]";
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

    public Order() {
    }


    // getters and setters...
}
