package com.shohamc1.foodavenue;

public class RestaurantData {
    protected String cuisine;
    protected String name_;
    protected int post_code;
    protected double rating;
    protected int resId;

    public RestaurantData(String cuisine, String name_, int post_code, double rating, int resId) {
        this.cuisine = cuisine;
        this.name_ = name_;
        this.post_code = post_code;
        this.rating = rating;
        this.resId=resId;
    }
}
