package com.shohamc1.foodavenue;

public class FoodData {
    protected String dishName;
    protected String cuisine;
    protected String description;
    protected int imageId;
    protected Double rating;

    public FoodData(String dishName, String cuisine, String description, int imageId, Double rating) {
        this.dishName = dishName;
        this.cuisine = cuisine;
        this.description=description;
        this.imageId = imageId;
        this.rating = rating;
    }
}
