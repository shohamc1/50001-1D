package com.shohamc1.foodavenue;

import java.util.ArrayList;
import java.util.Collections;

public class cuisine_sorting_food {
    public void sorting_cuisine() {
        String target = FoodData.cuisine;

        ArrayList<String> list = new ArrayList<String>();
        for (String temp : target.split(" ")) {
            list.add(temp);
        }

        Collections.sort(list);


    }
}

