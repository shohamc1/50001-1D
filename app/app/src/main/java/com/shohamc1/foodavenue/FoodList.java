package com.shohamc1.foodavenue;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Distribution;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class FoodList extends MainActivity{
    RecyclerView recyclerView;
    private LinkedList<String> dishnames = new LinkedList<>();
    private LinkedList<Integer> images = new LinkedList<>();

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_list);


        recyclerView = findViewById(R.id.charaRecyclerView);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CharaAdapter adpter = new CharaAdapter(dishnames);
        recyclerView.setAdapter(adpter);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ));
        //for no.of food items
        //i.create_card();

    }


    protected void create_card(String name){

        //text.setText(food in database);
        //image.setImage(image of food);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("dishes")
                //.whereEqualTo("cuisine", "Desserts")
                //.orderBy("rating", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot doc: task.getResult()) {
                                System.out.println(doc.getData());
                                // add to card list
                                dishnames.add((String) doc.getData().get("dish"));
                            }
                        } else {
                            Log.i("e", "Could not get Firebase data");
                        }
                    }
                });
    }




}





