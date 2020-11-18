package com.shohamc1.foodavenue;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseUtils {
    static QuerySnapshot result;

    public static QuerySnapshot getRestaurants(String cuisineName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("restaurants").whereEqualTo("cuisine", cuisineName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    result = task.getResult();
                    System.out.println("FUNC" + task.getResult());
                } else {
                    Log.i("e", "Could not Firebase data");
                }
            }
        });

        return result;
    }
}