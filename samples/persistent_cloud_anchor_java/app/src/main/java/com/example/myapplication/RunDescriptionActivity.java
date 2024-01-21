package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.structures.runs.Run;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.examples.java.persistentcloudanchor.CloudAnchorActivity;
import com.google.ar.core.examples.java.persistentcloudanchor.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RunDescriptionActivity extends AppCompatActivity {
    Run demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_description);

        for (Run run : MainActivity.runArrayList) {
            if(run.getName().equals(Run.SELECTED_RUN_NAME)){
                demo = run;
                break;
            }
        }

        TextView titleView = findViewById(R.id.run_title);
        TextView descView = findViewById(R.id.run_desc_view);

        titleView.setText(demo.getName());
        descView.setText(demo.getDesc());

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
                Intent intent = CloudAnchorActivity.newResolvingIntent(RunDescriptionActivity.this, (ArrayList<String>) Collections.singletonList(demo.id));
                startActivity(intent);
            }
        });
    }
}