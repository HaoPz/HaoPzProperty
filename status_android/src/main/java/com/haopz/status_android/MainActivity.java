package com.haopz.status_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView colorStatus = findViewById(R.id.colorStatus);
        TextView imgStatus = findViewById(R.id.imgStatus);
        TextView MOsStatus = findViewById(R.id.MOsStatus);

        colorStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ColorStatusActivity.class));
            }
        });

        imgStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ImgStatusActivity.class));
            }
        });

        MOsStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MOSStatusActivity.class));
            }
        });
    }
}
