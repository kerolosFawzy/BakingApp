package com.massive.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.massive.bakingapp.R;
import com.massive.bakingapp.interfaces.CallBack;
import com.massive.bakingapp.models.Steps;

import java.util.ArrayList;


public class stepsAdapter extends RecyclerView.Adapter<stepsAdapter.ViewHolder> {

    CallBack callBack;
    private ArrayList<Steps> steps;
    private Context mContext;

    public stepsAdapter(ArrayList<Steps> steps, Context context, CallBack callBack) {
        this.steps = steps;
        this.mContext = context;
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.steps_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.step.setText(steps.get(position).getShortDescription());
        holder.step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.getId((int) steps.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView step;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            step = view.findViewById(R.id.stepName);
            imageView = view.findViewById(R.id.imageView2);
        }
    }
}
