package com.mycompany.demo.entities;

import java.util.List;

public class OrderWithPizzas {
    private int id;
    private int idClient;
    private String status;
    private List<Integer> pizzas;
    private String fullName;

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

    public OrderWithPizzas() {
    }

    public OrderWithPizzas(int id, int idClient, String status, List<Integer> pizzas, String fullName) {
        this.id = id;
        this.idClient = idClient;
        this.status = status;
        this.pizzas = pizzas;
        this.fullName = fullName;
    }

    public List<Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Integer> pizzas) {
        this.pizzas = pizzas;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // getters and setters...
}
