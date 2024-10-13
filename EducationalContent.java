package com.example.dogfoodapp;

import android.net.Uri;

public class EducationalContent {
    private String title;
    private String description;
    private Uri videoUri;

    public EducationalContent(String title, String description, Uri videoUri) {
        this.title = title;
        this.description = description;
        this.videoUri = videoUri;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Uri getVideoUri() {
        return videoUri;
    }
}
