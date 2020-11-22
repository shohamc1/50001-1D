package com.shohamc1.foodavenue;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FoodList extends MainActivity{
    RecyclerView recyclerView;

    private LinkedList<Integer> images = new LinkedList<>();
    private LinkedList<String> cuisines = new LinkedList<>();


    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_list);


        recyclerView = findViewById(R.id.charaRecyclerView);
        CharaAdapter adapter = new CharaAdapter(get_dishes());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ));

    }


    public static LinkedList<String> get_dishes(){
        final LinkedList<String> dishnames = new LinkedList<>();
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
        return dishnames;
    }
    public HashMap<String,String> ListtoMap(List<String> keys, List<String> values) {
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





