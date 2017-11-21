package com.massive.bakingapp.models;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by minafaw on 11/21/2017.
 */

public class Steps implements Serializable {
    private String id;
    @Nullable
    private String shortDescription;
    private String description;
    @Nullable
    private String videoURL;
    @Nullable
    private String thumbnailURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Nullable
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(@Nullable String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(@Nullable String videoURL) {
        this.videoURL = videoURL;
    }

    @Nullable
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(@Nullable String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
