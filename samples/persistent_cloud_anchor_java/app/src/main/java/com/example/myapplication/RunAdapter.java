package com.example.myapplication;

import static android.content.Intent.ACTION_VIEW;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.structures.runs.Run;
import com.google.android.material.textfield.TextInputLayout;
import com.google.ar.core.examples.java.persistentcloudanchor.R;

import java.util.ArrayList;

public class RunAdapter extends RecyclerView.Adapter<RunAdapter.ViewHolder> {
    public Context viewContext;
    private final ArrayList<Run> runList;

    public RunAdapter(Context context, ArrayList<Run> runList){
        this.viewContext = context;
        this.runList = runList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.run_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Run curRun = runList.get(position);
        holder.runImageView.setImageResource(curRun.getPicturePath());
        holder.runNameView.setText(curRun.getName());
        holder.runDescView.setText(curRun.getDesc());
        setupClickEvent(holder);

    }

    @Override
    public int getItemCount() {
        return runList.size();
    }

    public void setupClickEvent(ViewHolder holder){
        // Define click listener for the ViewHolder's View
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    v.setBackgroundColor(Color.parseColor("#FFAFFF"));

                    Log.d("Pressed", "Button pressed");
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    v.setBackgroundColor(Color.parseColor("#FFFFFF"));

                    Run.SELECTED_RUN_NAME = holder.runNameView.getText().toString();

                    Intent intent = new Intent(viewContext, RunDescriptionActivity.class);
                    viewContext.startActivity(intent);
                    Log.d("Released", "Button released");
                }
                return false;
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView runImageView;
        private final TextView runNameView;
        private final TextView runDescView;

        public ViewHolder(View runView) {
            super(runView);

            runImageView = itemView.findViewById(R.id.card_run_image);
            runNameView = itemView.findViewById(R.id.card_run_name);
            runDescView = itemView.findViewById(R.id.card_run_desc);
        }
    }
}
