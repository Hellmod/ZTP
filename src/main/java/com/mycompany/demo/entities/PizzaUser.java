package com.mycompany.demo.entities;

import com.mycompany.demo.enums.RolePizza;

public class PizzaUser {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String roleUser;
    //private RolePizza roleUser;


    protected PizzaUser(int id, String name, String ingredients, int price) {
        this.id = id;
        this.username = name;
        this.password = ingredients;
    }

    public PizzaUser() {
    }


    @Override
    public String toString() {
        return "User [" + id + ", "+ username + ", " + password + ", " + fullName + ", " + roleUser + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // getters and setters...
}
