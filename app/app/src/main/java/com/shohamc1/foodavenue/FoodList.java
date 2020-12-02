package com.shohamc1.foodavenue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.base.CaseFormat;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FoodList extends MainActivity {
    RecyclerView recyclerView;
    LinkedList<FoodData> dishDatas = new LinkedList<>();
    Button button2;

    //private LinkedList<Integer> images = new LinkedList<>();
    //private LinkedList<String> cuisines = new LinkedList<>();


    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_list);
        recyclerView = findViewById(R.id.charaRecyclerView);
        get_dishes();


    }

    public String toCamelCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }


    public void get_dishes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("dishes")
                //.whereEqualTo("cuisine", "Desserts")
                .orderBy("rating", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot doc : task.getResult()) {
                                // add to card list
                                String dishName = (String) doc.getData().get("dish");
                                dishName = toCamelCase(dishName);
                                String cuisine = (String) doc.getData().get("cuisine");
                                String description = (String) doc.getData().get("description");
                                Double rating = (Double) doc.getData().get("rating");

                                int resId;
                                Context ctx = getBaseContext();
                                String imageName= new String(dishName);
                                imageName=transferString(imageName);
                                resId = getResources().getIdentifier(imageName, "drawable", ctx.getPackageName());
                                if (resId == 0) {
                                    if (dishName.equals("Biryani (indian Variant) Or Nasi Briyani (malay Variant)")) {
                                        resId = R.drawable.biryani;
                                    }
                                    else {
                                        resId = R.drawable.default_food;
                                    }
                                }
                                dishDatas.add(new FoodData(dishName, cuisine, description, resId, rating));
                                //System.out.println(dishName);
                            }
                        } else {
                            Log.i("e", "Could not get Firebase data");
                        }

                        final CharaAdapter adapter = new CharaAdapter(dishDatas);
                        recyclerView.setLayoutManager(new LinearLayoutManager(FoodList.this));
                        adapter.setOnItemClickLitener(new CharaAdapter.OnItemClickLitener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Toast.makeText(FoodList.this,"this is"+dishDatas.get(position).dishName,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(FoodList.this, RestaurantList.class);
                                LinkedList<FoodData> filterData = adapter.getFiltedData();
                                intent.putExtra("Description", filterData.get(position).description);
                                intent.putExtra("DishName", filterData.get(position).dishName);
                                intent.putExtra("ImageId", filterData.get(position).imageId);
                                intent.putExtra("Cuisine", filterData.get(position).cuisine);
                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                        EditText et = findViewById(R.id.search_bar);
                        et.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence sequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence sequence, int i, int i1, int i2) {
                                adapter.getFilter().filter(sequence.toString().toLowerCase());
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                    }
                });
    }

    public String transferString(String s) {

        s = s.replaceAll("[^a-zA-Z]", "");
        s=s.toLowerCase();

        return s;
    }
}





