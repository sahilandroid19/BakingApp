package com.example.sahil.bakingapp.ui;


import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sahil.bakingapp.R;
import com.example.sahil.bakingapp.model.RecipeStep;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StepDetailFragment extends Fragment implements View.OnClickListener {

    private View view;

    private SimpleExoPlayerView playerView;
    private ExoPlayer player;

    private int position;
    private ArrayList<RecipeStep> steps;
    private boolean mTwoPane;

    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;

    public StepDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_step_detail, container, false);

        if(savedInstanceState == null){
            playWhenReady = true;
            currentWindow = 0;
            playbackPosition = 0;
        }else {
            playWhenReady = savedInstanceState.getBoolean("playWhenReady");
            currentWindow = savedInstanceState.getInt("currentWindow");
            playbackPosition = savedInstanceState.getLong("playBackPosition");
        }

        if(getArguments() != null) {
            position = getArguments().getInt("position", 0);
            steps = getArguments().getParcelableArrayList("steps");
            mTwoPane = getArguments().getBoolean("mTwoPane");
        }

        playerView = view.findViewById(R.id.video_view);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT || mTwoPane){
            setStepTexts(position);
            view.findViewById(R.id.previous).setOnClickListener(this);
            view.findViewById(R.id.next).setOnClickListener(this);
        }


        return view;
    }

    private void setStepTexts(int pos){
        ((TextView) view.findViewById(R.id.step_short_description)).setText(steps.get(pos-1).getShort_description());
        ((TextView) view.findViewById(R.id.step_description)).setText(steps.get(pos-1).getDescription());
    }

    private void initializePayer(){
        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        passVideoUri(position);
        playerView.setPlayer(player);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

    }

    private void passVideoUri(int pos){
        Uri uri = Uri.parse(steps.get(pos-1).getVideo_url());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri){
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).createMediaSource(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        initializePayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        playbackPosition = player.getCurrentPosition();
        currentWindow = player.getCurrentWindowIndex();
        playWhenReady = player.getPlayWhenReady();

        outState.putBoolean("playWhenReady", playWhenReady);
        outState.putInt("currentWindow", currentWindow);
        outState.putLong("playBackPosition", playbackPosition);
        super.onSaveInstanceState(outState);
    }

    private void releasePlayer(){
        playbackPosition = player.getCurrentPosition();
        currentWindow = player.getCurrentWindowIndex();
        playWhenReady = player.getPlayWhenReady();
        player.release();
        player = null;
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.previous){
            if(position > 1) {
                position--;
                setStepTexts(position);
                passVideoUri(position);
            }
        }
        else if (view.getId() == R.id.next){
            if(position < steps.size()) {
                position++;
                setStepTexts(position);
                passVideoUri(position);
            }
        }
    }
}
