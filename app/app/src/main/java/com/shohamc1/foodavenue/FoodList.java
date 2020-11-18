package com.shohamc1.foodavenue;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Distribution;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FoodList extends MainActivity{
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

        //text.setText(food in database);
        //image.setImage(image of food);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("restaurants")
                .whereEqualTo("cuisine", "Desserts")
                .orderBy("rating", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot doc: task.getResult()) {
                                System.out.println(doc.getData());
                                // add to card list
                            }
                        } else {
                            Log.i("e", "Could not get Firebase data");
                        }
                    }
                });
    }


}





