package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.structures.runs.SimpleRun;
import com.google.android.material.textfield.TextInputEditText;
import com.google.ar.core.examples.java.persistentcloudanchor.CloudAnchorActivity;
import com.google.ar.core.examples.java.persistentcloudanchor.R;

public class CreationActivity extends AppCompatActivity {
    public static String idCreation = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        TextInputEditText nameTxt = findViewById(R.id.name_txt);
        TextInputEditText descTxt = findViewById(R.id.desc_txt);
        Button scanBtn = findViewById(R.id.scan_button);


        Button submitBtn = findViewById(R.id.submit_button);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CloudAnchorActivity.newHostingIntent(CreationActivity.this);
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.runArrayList.add(new SimpleRun(idCreation, nameTxt.getText().toString(), descTxt.getText().toString()));
                finish();
            }
        });
    }
}