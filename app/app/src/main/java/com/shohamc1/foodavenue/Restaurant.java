package com.shohamc1.foodavenue;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Restaurant extends AppCompatActivity {

    //private LinkedList<Integer> images = new LinkedList<>();
    //private LinkedList<String> cuisines = new LinkedList<>();
    TextView rest_text;
    TextView rest_cuisineText;
    TextView rest_rating;
    Button buttonConvert;
    ImageView back;

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        Intent intentFromRestaurant = getIntent();
        String name = intentFromRestaurant.getStringExtra("Name_");
        int postCode = intentFromRestaurant.getIntExtra("PostCode",000000);
        //int imageId = intentFromRestaurant.getIntExtra("ResId", R.drawable.default_food);
        Double rating=intentFromRestaurant.getDoubleExtra("Rating", R.drawable.default_food);
        String cuisine = intentFromRestaurant.getStringExtra("Cuisine");
        final String location=new Integer(postCode).toString();

        buttonConvert=findViewById(R.id.buttonToMap);
        rest_cuisineText=findViewById(R.id.rest_cuisine);
        rest_text=findViewById(R.id.rest_text);
        rest_rating=findViewById(R.id.rest_rating);
        back=findViewById(R.id.rest_back);
        rest_rating.setText(rating.toString());
        rest_cuisineText.setText(cuisine);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        try {
                            Instrumentation inst = new Instrumentation();
                            inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                        } catch (Exception e) {
                            Log.i("Error when Back", e.toString());
                        }
                    }
                }.start();
            }
        });
        System.out.println(name);
        rest_text.setText(name);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri.Builder builder=new Uri.Builder();
                builder.scheme("geo")
                        .opaquePart("0.0")
                        .appendQueryParameter("q",location);
                Uri uriGeoLocation=builder.build();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uriGeoLocation);
                try {
                    startActivity(intent);
                }
                catch(Exception e){
                    Toast.makeText(Restaurant.this,"Map app not found",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
