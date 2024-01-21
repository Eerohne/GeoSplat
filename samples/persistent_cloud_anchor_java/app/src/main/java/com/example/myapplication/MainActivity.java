package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.myapplication.structures.runs.Run;
import com.example.myapplication.structures.runs.SimpleRun;
import com.google.android.material.textfield.TextInputEditText;
import com.google.ar.core.examples.java.persistentcloudanchor.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView runCardPanel;
    ArrayList<Run> runArrayList;

    public static final String PREF = "PREF";
    private static final String KEY = "KEY_DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loadSaveFile();

        runCardPanel = findViewById(R.id.game_cards_panel);

        initializeSearchFunction();

        // Test the pane
        runArrayList = new ArrayList<>();
        loadSaveFile();
        runArrayList.add(new SimpleRun("JMSB Hunt" + new Random().nextInt(), "Roam Concordia and discover its amazing campuses", 0));
        //runArrayList.add(new SimpleRun("Hall Hunt", "Roam Concordia and discover its amazing campuses", 0));
        //runArrayList.add(new SimpleRun("McGill Hunt", "Roam McGill and discover its amazing campuses", 0));
        //runArrayList.add(new SimpleRun("UdeM Hunt", "Roam UdeM and discover its amazing campuses", 0));
        RunAdapter runAdapter = new RunAdapter(this, runArrayList);

        runCardPanel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        runCardPanel.setAdapter(runAdapter);

        storeSaveFile();
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

    public void storeSaveFile()
    {
        String convertedData = new Gson().toJson(runArrayList); // converted to string.

        SharedPreferences userDetails = this.getSharedPreferences("runDetails", MODE_PRIVATE);
        SharedPreferences.Editor edit = userDetails.edit();

        edit.putString("runList", convertedData);
        edit.apply();
    }

    public void loadSaveFile()
    {
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences("runDetails", Context.MODE_PRIVATE);
        String GsonSaveString = sharedPref.getString("runList", "");

        Type listType = new TypeToken<List<Run>>() {}.getType();
        ArrayList<Run> saveData = new Gson().fromJson(GsonSaveString,  listType);
        if(saveData != null)
            runArrayList = saveData;
        else
            runArrayList = new ArrayList<>();
    }
}