package com.example.sahil.bakingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.Recipe;
import com.example.sahil.bakingapp.ui.DetailActivity;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {
    private ArrayList<Recipe> recipes;
    private Context context;

    public RecipeAdapter(Context context, ArrayList<Recipe> recipes){
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.recipeName.setText(recipe.getRecipeName());
        String quantity = context.getString(R.string.quantity, recipe.getServings());
        holder.recipeQuantity.setText(quantity);

        switch (position){
            case 0:
                holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.nutella_pie));
                break;
            case 1:
                holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.brownies));
                break;
            case 2:
                holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.yellow_cake));
                break;
            case 3:
                holder.recipeImage.setImageDrawable(context.getResources().getDrawable(R.drawable.cheese_cake));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(recipes == null){
            return 0;
        }else {
            return recipes.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView recipeImage;
        private TextView recipeName, recipeQuantity;

        MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            recipeImage = itemView.findViewById(R.id.recipe_image);
            recipeName = itemView.findViewById(R.id.recipe_name);
            recipeQuantity = itemView.findViewById(R.id.recipe_quantity);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(context.getString(R.string.intent_recipe), recipes.get(getLayoutPosition()));
            context.startActivity(intent);
        }
    }

}
