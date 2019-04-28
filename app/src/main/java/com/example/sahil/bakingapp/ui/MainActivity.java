package com.example.sahil.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.sahil.bakingapp.utils.JsonUtils;
import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.Recipe;
import com.example.sahil.bakingapp.adapters.RecipeAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Recipe> recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            recipes = JsonUtils.getRecipes(this);
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.recipe_list);
        if(recyclerView.getTag().toString().equals("phone")) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else if(recyclerView.getTag().toString().equals("tablet")) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new RecipeAdapter(this, recipes));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
