package com.example.sahil.bakingapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.Recipe;
import com.example.sahil.bakingapp.adapters.RecipeDetailAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepListFragment extends Fragment {


    public StepListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_step_list, container, false);
        Recipe recipe = new Recipe();

        if(getArguments() != null){
            recipe = getArguments().getParcelable("recipe");
        }

        RecyclerView recyclerView = view.findViewById(R.id.detail_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new RecipeDetailAdapter(getActivity(), recipe));

        return view;
    }

}
