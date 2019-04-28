package com.example.sahil.bakingapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.RecipeStep;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    private int position;
    private ArrayList<RecipeStep> steps;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getIntent() != null){
            position = getIntent().getIntExtra("position", 0);
            steps = getIntent().getParcelableArrayListExtra("steps");
            mTwoPane = getIntent().getBooleanExtra("mTwoPane", false);
        }

        if(savedInstanceState == null){

            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            bundle.putParcelableArrayList("steps", steps);
            bundle.putBoolean("mTwoPane", false);
            StepDetailFragment detailFragment = new StepDetailFragment();
            detailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.video_frame_layout, detailFragment)
                    .commit();
        }

    }

}
