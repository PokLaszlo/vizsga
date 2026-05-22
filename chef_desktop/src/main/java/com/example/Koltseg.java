package com.example;

import java.time.LocalDate;

public class Koltseg {
    private int id;
    private String chefname;
    private LocalDate date;
    private String category;
    private int price;
    private String comment;

    public Koltseg() {
    }
    public Koltseg(int id, String chefname, LocalDate date, String category, int price, String comment) {
        this.id = id;
        this.chefname = chefname;
        this.date = date;
        this.category = category;
        this.price = price;
        this.comment = comment;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getChefname() {
        return chefname;
    }
    public void setChefname(String chefname) {
        this.chefname = chefname;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    } 
}
