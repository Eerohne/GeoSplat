package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.myapplication.structures.runs.Run;
import com.example.myapplication.structures.runs.SimpleRun;
import com.google.android.material.textfield.TextInputEditText;
import com.google.ar.core.examples.java.persistentcloudanchor.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView runCardPanel;
    ArrayList<Run> runArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runCardPanel = findViewById(R.id.game_cards_panel);

        initializeSearchFunction();

        // Test the pane
        runArrayList = new ArrayList<>();
        runArrayList.add(new SimpleRun("JMSB Hunt", "Roam Concordia and discover its amazing campuses", 0));
        runArrayList.add(new SimpleRun("Hall Hunt", "Roam Concordia and discover its amazing campuses", 0));
        runArrayList.add(new SimpleRun("McGill Hunt", "Roam McGill and discover its amazing campuses", 0));
        runArrayList.add(new SimpleRun("UdeM Hunt", "Roam UdeM and discover its amazing campuses", 0));

        RunAdapter runAdapter = new RunAdapter(this, runArrayList);

        runCardPanel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        runCardPanel.setAdapter(runAdapter);
    }

    private void initializeSearchFunction(){
        TextInputEditText searchText = findViewById(R.id.search_bar_text);

        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Run> newRunList = new ArrayList<>();
                if(!s.toString().isEmpty()){
                    for (Run r : runArrayList) {
                        if(r.getName().toLowerCase().contains(s.toString().trim().toLowerCase()) || r.getDesc().toLowerCase().contains(s.toString().trim().toLowerCase()))
                            newRunList.add(r);
                    }
                }
                else newRunList = runArrayList;

                //Log.d("test", "aaaaaaa");

                RunAdapter newRunAdapter = new RunAdapter(MainActivity.this, newRunList);
                runCardPanel.setAdapter(newRunAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}