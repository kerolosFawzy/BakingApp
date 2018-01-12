package com.massive.bakingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.massive.bakingapp.R;
import com.massive.bakingapp.models.Ingredients;

import java.util.ArrayList;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    ArrayList<Ingredients> ingredients;

    public IngredientAdapter( ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.ingre_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.ViewHolder holder, int position) {
        holder.ingredientName.setText(ingredients.get(position).getIngredient());
        String s= String.valueOf(ingredients.get(position).getQuantity());
        holder.quantity.setText(s);
        holder.measure.setText(ingredients.get(position).getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName, quantity, measure;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            quantity = itemView.findViewById(R.id.quantity);
            measure = itemView.findViewById(R.id.measure);
        }
    }

}
