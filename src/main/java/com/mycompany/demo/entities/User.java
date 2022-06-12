/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demo.entities;

import com.mycompany.demo.enums.Role;


public class User {
    protected String login;
    protected String pass;
    final protected Role role;

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.role = Role.CUSTOMER;
    }

    public User(String login, String pass, Role role) {
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPass() {
        return this.pass;
    }

    public Role getRole() {
        return role;
    }

}
