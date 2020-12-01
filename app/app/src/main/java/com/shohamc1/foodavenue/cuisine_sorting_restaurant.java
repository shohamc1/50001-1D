package com.shohamc1.foodavenue;

import java.util.ArrayList;
import java.util.Collections;

public class cuisine_sorting_restaurant {
    public void alphabet_sorting_food() {
        String target = RestaurantData.cuisine;

        ArrayList<String> list = new ArrayList<String>();
        for (String temp : target.split(" ")) {
            list.add(temp);
        }

        Collections.sort(list);


    }
}
