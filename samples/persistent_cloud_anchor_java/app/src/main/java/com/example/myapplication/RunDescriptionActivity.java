package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.structures.runs.Run;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.examples.java.persistentcloudanchor.CloudAnchorActivity;
import com.google.ar.core.examples.java.persistentcloudanchor.R;

public class RunDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_description);

        FloatingActionButton fab = findViewById(R.id.back_button);
        Button gameStartButton = findViewById(R.id.start_game_button);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gameStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CloudAnchorActivity.newHostingIntent(RunDescriptionActivity.this);
                startActivity(intent);
            }
        });
    }
}