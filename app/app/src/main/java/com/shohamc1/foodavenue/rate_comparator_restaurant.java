package com.shohamc1.foodavenue;
import java.util.*;


public class rate_comparator_restaurant implements Comparator<RestaurantData>{


    public int compare(RestaurantData r1,RestaurantData r2){
        if (r1.rating-r2.rating>0){
            return 1;
        }
        return -1;
    }

}
