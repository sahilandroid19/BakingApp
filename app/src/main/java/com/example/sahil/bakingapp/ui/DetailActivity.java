package com.example.sahil.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.Recipe;
import com.example.sahil.bakingapp.adapters.RecipeDetailAdapter;

public class DetailActivity extends AppCompatActivity implements RecipeDetailAdapter.StepClicked {

    private Recipe recipe;

    private Boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTwoPane = findViewById(R.id.step_detail_frame_layout) != null;

        if(getIntent().getExtras() != null)
        recipe = getIntent().getExtras().getParcelable(getString(R.string.intent_recipe));

        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe", recipe);
        StepListFragment listFragment = new StepListFragment();
        listFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.detail_frame_layout, listFragment)
                .commit();

        if(mTwoPane){

            Bundle bundle1 = new Bundle();
            bundle1.putInt("position", 1);
            bundle1.putParcelableArrayList("steps", recipe.getSteps());
            bundle1.putBoolean("mTwoPane", true);
            StepDetailFragment detailFragment = new StepDetailFragment();
            detailFragment.setArguments(bundle1);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_frame_layout, detailFragment)
                    .commit();
        }


    }

    @Override
    public void onStepClick(int position) {

        if(!mTwoPane) {
            Intent intent = new Intent(this, VideoActivity.class);
            intent.putExtra("position", position);
            intent.putParcelableArrayListExtra("steps", recipe.getSteps());
            startActivity(intent);
        }else {
            Bundle bundle1 = new Bundle();
            bundle1.putInt("position", position);
            bundle1.putParcelableArrayList("steps", recipe.getSteps());
            bundle1.putBoolean("mTwoPane", true);
            StepDetailFragment detailFragment = new StepDetailFragment();
            detailFragment.setArguments(bundle1);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_detail_frame_layout, detailFragment)
                    .commit();
        }
    }
}
