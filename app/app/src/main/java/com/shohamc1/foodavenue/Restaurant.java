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
    TextView rest_subtext;
    Button buttonConvert;
    ImageView back;

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant);

        Intent intentFromRestaurant = getIntent();
        final String name = intentFromRestaurant.getStringExtra("Name_");

        buttonConvert = findViewById(R.id.buttonToMap);
        rest_subtext = findViewById(R.id.rest_subtext);
        rest_text = findViewById(R.id.rest_text);
        back = findViewById(R.id.rest_back);
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
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo")
                        .opaquePart("0.0")
                        .appendQueryParameter("q", name);
                Uri uriGeoLocation = builder.build();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(uriGeoLocation);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(Restaurant.this, "Map app not found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
