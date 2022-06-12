package com.mycompany.demo.entities;

public class Cart {
    private int orderid;
    private int menuid;

    protected Cart(int cartid, int menuid) {
        this.orderid = cartid;
        this.menuid = menuid;
    }

    public Cart() {
    }


    @Override
    public String toString() {
        return "Pizza [" + orderid + ", " + menuid + "]";
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }
// getters and setters...
}
