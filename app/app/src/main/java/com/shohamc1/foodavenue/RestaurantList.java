package com.shohamc1.foodavenue;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RestaurantList extends AppCompatActivity {
    RecyclerView recyclerView;
    LinkedList<RestaurantData> restaurantDatas = new LinkedList<>();

    //private LinkedList<Integer> images = new LinkedList<>();
    //private LinkedList<String> cuisines = new LinkedList<>();
    TextView dish_text;
    TextView dish_subtext;
    ImageView dish_photo;
    ImageView back;

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);

        Intent intentFromFood = getIntent();
        String description = intentFromFood.getStringExtra("Description");
        String dishName = intentFromFood.getStringExtra("DishName");
        int imageId = intentFromFood.getIntExtra("ImageId", R.drawable.default_food);
        String cuisine = intentFromFood.getStringExtra("Cuisine");

        setContentView(R.layout.restaurant_list);
        recyclerView = findViewById(R.id.restaurantRecyclerView);
        get_restaurant(description, dishName, cuisine, imageId);
        back = findViewById(R.id.food_back);
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
        //CharaAdapter adapter = new CharaAdapter(dishDatas);
        //recyclerView.setLayoutManager( new LinearLayoutManager( this ));
        //recyclerView.setAdapter(adapter);
    }


    public void get_restaurant(final String description, final String dishName, final String food_cuisine, final int imageId) {
        //LinkedList<String> dishnames = new LinkedList<>();
        //text.setText(food in database);
        //image.setImage(image of food);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("restaurants")
                //.whereEqualTo("cuisine", "Desserts")
                .orderBy("rating", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                //System.out.println(doc.getData());
                                // add to card list
                                String cuisine = (String) doc.getData().get("cuisine");
                                System.out.println(cuisine);
                                if (cuisine.equals(food_cuisine)) {
                                    String name = (String) doc.getData().get("name");
                                    int post_code;
                                    if (doc.getData().get("post_code") instanceof Long) {
                                        Long post_code_long = (Long) doc.getData().get("post_code");
                                        post_code = post_code_long.intValue();
                                    } else {
                                        String post_code_string = (String) doc.getData().get("post_code");
                                        post_code = Integer.parseInt(post_code_string);
                                    }
                                    Double rating;
                                    if (doc.getData().get("rating") instanceof Long) {
                                        Long rating_long = (Long) doc.getData().get("rating");
                                        int rating_int = rating_long.intValue();
                                        rating = new Double(rating_int);
                                    } else {
                                        rating = (Double) doc.getData().get("rating");
                                    }


                                    int resId;
                                    Context ctx = getBaseContext();
                                    resId = getResources().getIdentifier(name, "drawable", ctx.getPackageName());
                                    if (resId == 0) {
                                        resId = resId = R.drawable.default_food;
                                    }
                                    restaurantDatas.add(new RestaurantData(cuisine, name, post_code, rating, resId));
                                    System.out.println(dishName);
                                }
                            }
                        } else {
                            Log.i("e", "Could not get Firebase data");
                        }

                        //System.out.println("Dish names: ");
                        System.out.println(restaurantDatas.toString());
                        RestaurantAdapter adapter = new RestaurantAdapter(restaurantDatas);
                        recyclerView.setLayoutManager(new LinearLayoutManager(RestaurantList.this));
                        adapter.setOnItemClickLitener(new RestaurantAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Toast.makeText(RestaurantList.this, "this is" + restaurantDatas.get(position).name_, Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RestaurantList.this, Restaurant.class);
                                intent.putExtra("Name_", restaurantDatas.get(position).name_);
                                intent.putExtra("PostCode", restaurantDatas.get(position).post_code);
                                intent.putExtra("ResId", restaurantDatas.get(position).resId);
                                intent.putExtra("Cuisine", restaurantDatas.get(position).cuisine);
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                        dish_photo = findViewById(R.id.dish_photo_r);
                        dish_text = findViewById(R.id.dish_text);
                        dish_subtext = findViewById(R.id.dish_subtext);

                        dish_photo.setImageResource(imageId);
                        dish_text.setText(dishName);
                        dish_subtext.setText(description);
                    }
                });
    }

    public HashMap<String, String> ListtoMap(List<String> keys, List<String> values) {
        HashMap map = new HashMap() {
        };
        Iterator<String> i1 = keys.iterator();
        Iterator<String> i2 = values.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            map.put(i1.next(), i2.next());
        }
        return map;
    }
}





