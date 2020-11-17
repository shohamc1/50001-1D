package com.shohamc1.foodavenue;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.api.Distribution;

public class FoodList extends MainActivity {


    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    LinearLayout dishlist = findViewById(R.id.dishlist);
    LinearLayout food_card = findViewById(R.id.food_card);
    TextView text = findViewById(R.id.foodtext);
    ImageView image = findViewById(R.id.foodimage);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_list);
        //for no.of food items
        //i.create_card();


    }

    protected void create_card(String name){
        food_card.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        View item = getLayoutInflater().inflate(R.layout.food_card, null, false);
        food_card.addView(item);
        dishlist.addView(food_card);
        //text.setText(food in database);
        //image.setImage(image of food);

    }


}





