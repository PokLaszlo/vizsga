package com.example;

import java.time.LocalDate;

public class Berles {
    private int uid;
    private int chefId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int daily_rate;
    private String name;
    private String cuisine;
    public Berles(int uid, int chefId, LocalDate startDate, LocalDate endDate, int daily_rate, String name,
            String cuisine) {
        this.uid = uid;
        this.chefId = chefId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daily_rate = daily_rate;
        this.name = name;
        this.cuisine = cuisine;
    }
    public Berles(int chefId, LocalDate startDate, LocalDate endDate, int daily_rate, String name, String cuisine) {
        this.chefId = chefId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daily_rate = daily_rate;
        this.name = name;
        this.cuisine = cuisine;
    }
    public Berles() {
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getChefId() {
        return chefId;
    }
    public void setChefId(int chefId) {
        this.chefId = chefId;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public int getDaily_rate() {
        return daily_rate;
    }
    public void setDaily_rate(int daily_rate) {
        this.daily_rate = daily_rate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCuisine() {
        return cuisine;
    }
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    public int TotalPrice(){
        return (int)(endDate.toEpochDay() - startDate.toEpochDay()) * daily_rate;
    }
}
