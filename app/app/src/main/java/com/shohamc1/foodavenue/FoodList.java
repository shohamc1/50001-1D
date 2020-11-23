package com.shohamc1.foodavenue;

import android.content.Context;
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

public class FoodList extends MainActivity{
    RecyclerView recyclerView;
    LinkedList<FoodData> dishDatas = new LinkedList<>();

    private LinkedList<Integer> images = new LinkedList<>();
    private LinkedList<String> cuisines = new LinkedList<>();


    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_list);
        recyclerView = findViewById(R.id.charaRecyclerView);
        get_dishes();
        //dishDatas.add(new FoodData("qwe","asd",R.drawable.default_food));
        //dishDatas.add(new FoodData("rty","asd",R.drawable.default_food));
        //dishDatas.add(new FoodData("uio","asd",R.drawable.default_food));
        //dishDatas.add(new FoodData("asd","asd",R.drawable.default_food));
        //dishDatas.add(new FoodData("fgh","asd",R.drawable.default_food));
        //dishDatas.add(new FoodData("jkl","asd",R.drawable.default_food));
        CharaAdapter adapter = new CharaAdapter(dishDatas);
        recyclerView.setLayoutManager( new LinearLayoutManager( this ));
        recyclerView.setAdapter(adapter);


    }




    public void get_dishes(){
        //LinkedList<String> dishnames = new LinkedList<>();
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
                                //System.out.println(doc.getData());
                                // add to card list
                                String dishName=(String) doc.getData().get("dish");
                                String cuisine=(String) doc.getData().get("cuisine");

                                int resId=R.drawable.default_food;
                                try {
                                    Context ctx=getBaseContext();
                                    resId = getResources().getIdentifier(dishName, "drawable", ctx.getPackageName());
                                } catch (Exception e) {
                                    resId=R.drawable.default_food;
                                }
                                finally {
                                    dishDatas.add(new FoodData(dishName,cuisine,resId));
                                }
                                //System.out.println(dishNames);
                            }
                        } else {
                            Log.i("e", "Could not get Firebase data");
                        }

                        System.out.println("Dish names: ");
                        System.out.println(dishDatas.toString());
                    }
                });
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





