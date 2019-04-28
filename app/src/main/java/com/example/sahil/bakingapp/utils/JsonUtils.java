package com.example.sahil.bakingapp.utils;

import android.content.Context;

import com.dezlum.codelabs.getjson.GetJson;
import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.Ingredients;
import com.example.sahil.bakingapp.model.Recipe;
import com.example.sahil.bakingapp.model.RecipeStep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class JsonUtils {

    public static ArrayList<Recipe> getRecipes(Context context) throws JSONException, ExecutionException, InterruptedException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(new GetJson().AsString(context.getString(R.string.json_url)));

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject recipeJson = jsonArray.getJSONObject(i);

            Recipe recipe = new Recipe();
            recipe.setRecipeName(recipeJson.getString("name"));
            recipe.setServings(recipeJson.getString("servings"));

            JSONArray ingredientsArray = recipeJson.getJSONArray("ingredients");
            ArrayList<Ingredients> ingredients = new ArrayList<>();
            for (int j = 0; j < ingredientsArray.length(); j++) {
                JSONObject ingredientObject = ingredientsArray.getJSONObject(j);
                Ingredients ingredient = new Ingredients();
                ingredient.setQuantity(ingredientObject.getString("quantity"));
                ingredient.setMeasure(ingredientObject.getString("measure"));
                ingredient.setName(ingredientObject.getString("ingredient"));
                ingredients.add(ingredient);
            }
            recipe.setIngredients(ingredients);

            JSONArray stepsArray = recipeJson.getJSONArray("steps");
            ArrayList<RecipeStep> steps = new ArrayList<>();
            ArrayList<String> steps_short_descriptions = new ArrayList<>();
            for (int k = 0; k < stepsArray.length(); k++) {
                JSONObject stepObject = stepsArray.getJSONObject(k);
                RecipeStep recipeStep = new RecipeStep();
                recipeStep.setShort_description(stepObject.getString("shortDescription"));
                recipeStep.setDescription(stepObject.getString("description"));
                recipeStep.setVideo_url(stepObject.getString("videoURL"));
                steps.add(recipeStep);
            }
            recipe.setSteps(steps);
            recipes.add(recipe);
        }
        return recipes;
    }
}
