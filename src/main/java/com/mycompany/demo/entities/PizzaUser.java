package com.mycompany.demo.entities;

import com.mycompany.demo.enums.RolePizza;

public class PizzaUser {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String UserGroup;


    protected PizzaUser(int id, String username, String password, String fullName, String UserGroup) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.UserGroup = UserGroup;
    }

    public PizzaUser() {
    }


    @Override
    public String toString() {
        return "User [" + id + ", "+ username + ", " + password + ", " + fullName + ", " + UserGroup + "]";
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

    public String getUserGroup() {
        return UserGroup;
    }

    public void setUserGroup(String UserGroup) {
        this.UserGroup = UserGroup;
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
