package com.massive.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.massive.bakingapp.R;
import com.massive.bakingapp.models.Steps;

import java.util.ArrayList;

/**
 * Created by minafaw on 1/9/2018.
 */

public class stepsAdapter extends RecyclerView.Adapter<stepsAdapter.ViewHolder> {

    ArrayList<Steps> steps;

    public stepsAdapter(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.steps_content,parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.step.setText(steps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView step;

        public ViewHolder(View view) {
            super(view);
            step = view.findViewById(R.id.stepName);
        }
    }
}
