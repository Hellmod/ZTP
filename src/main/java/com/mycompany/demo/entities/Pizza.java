package com.mycompany.demo.entities;

public class Pizza {
    private int id;
    private String name;
    private String ingredients;
    private int price;

    protected Pizza(int id, String name, String ingredients, int price) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    public Pizza() {
    }


    @Override
    public String toString() {
        return "Pizza [" + id + ", "+ name + ", " + ingredients + ", $" + price + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    // getters and setters...
}
