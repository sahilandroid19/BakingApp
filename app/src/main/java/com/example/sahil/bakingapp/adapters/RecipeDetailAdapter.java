package com.example.sahil.bakingapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.sahil.bakingapp.model.Ingredients;
import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.Recipe;
import com.example.sahil.bakingapp.utils.Utility;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Recipe recipe;
    private StepClicked stepClicked;

    public RecipeDetailAdapter(Context context, Recipe recipe){
        this.context = context;
        this.recipe = recipe;
        this.stepClicked = (StepClicked) context;
    }

    public interface StepClicked{
         public void onStepClick(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:
                return new MyViewHolder1(LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.detail_row_item_1, parent, false));
            case 1:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.detail_row_item_2, parent, false));
            default:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.detail_row_item_2, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int type = holder.getItemViewType();
        switch (holder.getItemViewType()){
            case 0:
                MyViewHolder1 myViewHolder1 = (MyViewHolder1) holder;
                for(int i=0; i<recipe.getIngredients().size(); i++){
                    Ingredients ingredient = recipe.getIngredients().get(i);
                    for(int j=0; j<myViewHolder1.gridLayout.getColumnCount(); j++) {
                        if (j == 0) {
                            myViewHolder1.gridLayout.addView(Utility.getIngredientNameView(context, ingredient.getName()));
                        } else {
                            myViewHolder1.gridLayout.addView(Utility.getIngredientQuantityView
                                    (context, ingredient.getQuantity(), ingredient.getMeasure()));
                        }
                    }
                }
                break;

            case 1:
                MyViewHolder2 myViewHolder2 = (MyViewHolder2) holder;
                myViewHolder2.textView.setText(recipe.getSteps().get(position-1).getShort_description());

        }

    }

    @Override
    public int getItemCount() {
        return recipe.getSteps().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        }else {
            return 1;
        }
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder{
        private GridLayout gridLayout;

        MyViewHolder1(View itemView) {
            super(itemView);
            gridLayout = itemView.findViewById(R.id.ingredient_layout);
            gridLayout.setRowCount(recipe.getIngredients().size());

        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView;

        MyViewHolder2(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.detail_step_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            stepClicked.onStepClick(getLayoutPosition());
        }
    }

}
