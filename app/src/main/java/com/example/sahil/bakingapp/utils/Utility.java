package com.example.sahil.bakingapp.utils;

import android.content.Context;
import android.widget.TextView;

import com.example.sahil.bakingapp.R;

public class Utility {

    public static TextView getIngredientNameView(Context context, String name){
        TextView textView = new TextView(context);
        textView.setTextSize(18);
        textView.setText(name);
        textView.setMaxLines(4);
        textView.setMaxEms(10);
        textView.setTextColor(context.getResources().getColor(android.R.color.white));

        /*
        if(getIntent().getExtras() !=null) {
            recipe = getIntent().getExtras().getParcelable(getString(R.string.intent_recipe));
        }

        GridLayout gridLayout = findViewById(R.id.ingredient_layout);
        gridLayout.setRowCount(recipe.getIngredients().size());

        for(int i=0; i<recipe.getIngredients().size(); i++){
            Ingredients ingredient = recipe.getIngredients().get(i);
            for(int j=0; j<gridLayout.getColumnCount(); j++) {
                if (j == 0) {
                    gridLayout.addView(Utility.getIngredientNameView(this, ingredient.getName()));
                } else {
                    gridLayout.addView(Utility.getIngredientQuantityView
                            (this, ingredient.getQuantity(), ingredient.getMeasure()));
                }
            }
        }
        */

        return textView;
    }

    public static TextView getIngredientQuantityView(Context context, String Qty, String measurment){
        String value = context.getResources().getString(R.string.ingredient_quantity, Qty, measurment);
        TextView textView = new TextView(context);
        textView.setTextSize(16);
        textView.setMaxLines(2);
        textView.setMaxEms(10);
        textView.setText(value);
        textView.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));
        return textView;
    }
}
