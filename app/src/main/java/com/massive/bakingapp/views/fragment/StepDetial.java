package com.massive.bakingapp.views.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.massive.bakingapp.R;
import com.massive.bakingapp.models.Steps;
import com.massive.bakingapp.views.activity.RecipeDetailsActivity;

public class StepDetial extends Fragment {


    Uri videoUri;
    int id;
    Intent intent;
    private Steps steps;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.step_detial_fragment, container, false);
        TextView description=RootView.findViewById(R.id.Description);
        Button Next = RootView.findViewById(R.id.Next);
        Button previous = RootView.findViewById(R.id.Previous);
        simpleExoPlayerView = new SimpleExoPlayerView(getActivity());
        simpleExoPlayerView = RootView.findViewById(R.id.videoView);

        Bundle bundle = getArguments();
        id = bundle.getInt("StepId");
        if (id == CardFragment.steps.size()-1)
            Next.setVisibility(View.GONE);

        if (id == 0)
            previous.setVisibility(View.GONE);

        steps = CardFragment.steps.get(id);

        exoplayerShow();
        intent = new Intent(getActivity(), RecipeDetailsActivity.class);

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("StepId", id - 1);
                startActivity(intent);
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("StepId", id + 1);
                startActivity(intent);
            }
        });
        description.setText(steps.getDescription());
        return RootView;
    }


    private void exoplayerShow() {
        String userAgent = Util.getUserAgent(getActivity(), "bakingapp");
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector selector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        player = ExoPlayerFactory.newSimpleInstance(getActivity(), selector);
        Handler handler = new Handler();
        if (!steps.getThumbnailURL().isEmpty()) {
            videoUri = Uri.parse(steps.getThumbnailURL());
        } else if (!steps.getVideoURL().isEmpty()) {
            videoUri = Uri.parse(steps.getVideoURL());
        } else {
            simpleExoPlayerView.setVisibility(View.GONE);
        }
        if (videoUri != null) {
            DefaultHttpDataSourceFactory sourceFactory = new DefaultHttpDataSourceFactory(userAgent);
            MediaSource source = new ExtractorMediaSource
                    (videoUri, sourceFactory, new DefaultExtractorsFactory(), handler, null);
            simpleExoPlayerView.setPlayer(player);
            simpleExoPlayerView.setUseController(true);
            simpleExoPlayerView.requestFocus();

            player.prepare(source);
            player.setPlayWhenReady(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.release();
    }
}
