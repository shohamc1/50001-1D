package com.shohamc1.foodavenue;
import java.util.ArrayList;
import java.util.Collections;

public class alphabet_sorting_food {


    public void alphabet_sorting_food() {
        String target = FoodData.dishName;

        ArrayList<String> list = new ArrayList<String>();
        for (String temp : target.split(" ")) {
            list.add(temp);
        }

        Collections.sort(list);


    }

}
