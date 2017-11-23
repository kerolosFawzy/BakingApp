package com.massive.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.massive.bakingapp.interfaces.CallBack;
import com.massive.bakingapp.models.Recipe;

import java.util.ArrayList;

/**
 * Created by minafaw on 11/22/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<Recipe> Recipe;
    private CallBack callBack;
    private Context mContext;

    public CardAdapter(ArrayList<Recipe> Recipe, CallBack callBack, Context context) {
        this.Recipe = Recipe;
        this.callBack = callBack;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(mContext)
                .load(Recipe.get(position).getImage())
                .error(R.drawable.file)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.Poster);

        holder.Name.setText(Recipe.get(position).getName());
        holder.Number.setText(Recipe.get(position).getServings());

        holder.Poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.getData(Recipe.get(position).getIngredients(),Recipe.get(position).getSteps());
            }
        });
    }

    @Override
    public int getItemCount() {
        return Recipe.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Poster;
        TextView Name, Number;

        public ViewHolder(View itemView) {
            super(itemView);
            Poster = itemView.findViewById(R.id.PosterImage);
            Name = itemView.findViewById(R.id.FoodName);
            Number = itemView.findViewById(R.id.FoodNumber);
        }
    }

}
