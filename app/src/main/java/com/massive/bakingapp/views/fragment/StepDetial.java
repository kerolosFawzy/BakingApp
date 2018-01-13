package com.massive.bakingapp.views.fragment;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by minafaw on 1/12/2018.
 */

public class StepDetial extends Fragment {

    Uri videoUri;
    private Steps steps;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer player;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.step_detial_fragment, container, false);
        TextView description = new TextView(getActivity());
        description = RootView.findViewById(R.id.Description);
        simpleExoPlayerView = new SimpleExoPlayerView(getActivity());
        simpleExoPlayerView = RootView.findViewById(R.id.videoView);
        Bundle bundle = getArguments();
        int id = bundle.getInt("StepId");
        steps = CardFragment.steps.get(id);
        description.setText(steps.getDescription());
        exoplayerShow();
        //showData();
        return RootView;
    }


    private void exoplayerShow() {
        String userAgent = Util.getUserAgent(getActivity(), "bakingapp");
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector selector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        player = ExoPlayerFactory.newSimpleInstance(getActivity(), selector);
        Handler handler =new Handler();
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
            player.seekTo(0);
            player.prepare(source);
            player.setPlayWhenReady(true);
        }
    }


//    private void showData() {
//        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
//        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
//        LoadControl loadControl = new DefaultLoadControl();
//        player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
//        simpleExoPlayerView.setUseController(true);
//        simpleExoPlayerView.requestFocus();
//        simpleExoPlayerView.setPlayer(player);
//        if (!steps.getThumbnailURL().isEmpty()) {
//            videoUri = Uri.parse(steps.getThumbnailURL());
//        } else if (!steps.getVideoURL().isEmpty()) {
//            videoUri = Uri.parse(steps.getVideoURL());
//        } else {
//            simpleExoPlayerView.setVisibility(View.GONE);
//        }
//        if (videoUri != null) {
//            DefaultBandwidthMeter bandwidthMeterA = new DefaultBandwidthMeter();
//            DefaultDataSourceFactory dataSourceFactory =
//                    new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "bakingapp")
//                            , bandwidthMeterA);
//            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//            MediaSource videoSource =
//                    new HlsMediaSource(videoUri, dataSourceFactory, 1, null, null);
//            final LoopingMediaSource loopingSource = new LoopingMediaSource(videoSource);
//            player.prepare(loopingSource);
//            player.addListener(new ExoPlayer.EventListener() {
//                @Override
//                public void onTimelineChanged(Timeline timeline, Object manifest) {
//
//                }
//
//                @Override
//                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
//
//                }
//
//                @Override
//                public void onLoadingChanged(boolean isLoading) {
//
//                }
//
//                @Override
//                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//
//                }
//
//                @Override
//                public void onRepeatModeChanged(int repeatMode) {
//
//                }
//
//                @Override
//                public void onPlayerError(ExoPlaybackException error) {
//                    player.stop();
//                    player.prepare(loopingSource);
//                    player.setPlayWhenReady(true);
//                }
//
//                @Override
//                public void onPositionDiscontinuity() {
//
//                }
//
//                @Override
//                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
//
//                }
//            });
//            player.setPlayWhenReady(true);
//            // player.setVideoDebugListener(this);
//        }
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.release();
    }
}
