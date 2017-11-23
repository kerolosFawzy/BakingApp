package com.massive.bakingapp.models;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by minafaw on 11/21/2017.
 */

public class Steps  {
    private String description;
    private long id;
    private String shortDescription;
    private String thumbnailURL;
    private String videoURL;

    @JsonProperty("description")
    public String getDescription() { return description; }
    @JsonProperty("description")
    public void setDescription(String value) { this.description = value; }

    @JsonProperty("id")
    public long getId() { return id; }
    @JsonProperty("id")
    public void setId(long value) { this.id = value; }

    @JsonProperty("shortDescription")
    public String getShortDescription() { return shortDescription; }
    @JsonProperty("shortDescription")
    public void setShortDescription(String value) { this.shortDescription = value; }

    @JsonProperty("thumbnailURL")
    public String getThumbnailURL() { return thumbnailURL; }
    @JsonProperty("thumbnailURL")
    public void setThumbnailURL(String value) { this.thumbnailURL = value; }

    @JsonProperty("videoURL")
    public String getVideoURL() { return videoURL; }
    @JsonProperty("videoURL")
    public void setVideoURL(String value) { this.videoURL = value; }
}
