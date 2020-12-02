package com.shohamc1.foodavenue;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRestaurant extends AppCompatActivity {
    ImageView back;
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_res);

        Spinner staticSpinner = (Spinner) findViewById(R.id.cuisine);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.cuisines,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
        back = findViewById(R.id.food_back1);
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
        Button done = findViewById(R.id.button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameET = findViewById(R.id.name);
                String resName = nameET.getText().toString();

                EditText ratingET = findViewById(R.id.rating);
                Double rating = Double.parseDouble(ratingET.getText().toString());

                EditText postCodeET = findViewById(R.id.postCode);
                Integer postCode = Integer.parseInt(postCodeET.getText().toString());

                String cuisine = ((Spinner) findViewById(R.id.cuisine)).getSelectedItem().toString();
                Map<String, Object> pushObj = new HashMap<>();

                pushObj.put("name", resName);
                pushObj.put("post_code", postCode);
                pushObj.put("cuisine", cuisine);
                pushObj.put("rating", rating);

                System.out.println("HERE: " + pushObj.toString());

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("review").document(resName)
                        .set(pushObj)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Sent for review!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Error writing document", e);
                            }
                        });
            }
        });
    }
}
