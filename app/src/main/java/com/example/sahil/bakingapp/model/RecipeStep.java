package com.example.sahil.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeStep implements Parcelable {

    private String short_description;
    private String description;
    private String video_url;

    public RecipeStep(){}

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    protected RecipeStep(Parcel in) {
        short_description = in.readString();
        description = in.readString();
        video_url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(short_description);
        dest.writeString(description);
        dest.writeString(video_url);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecipeStep> CREATOR = new Parcelable.Creator<RecipeStep>() {
        @Override
        public RecipeStep createFromParcel(Parcel in) {
            return new RecipeStep(in);
        }

        @Override
        public RecipeStep[] newArray(int size) {
            return new RecipeStep[size];
        }
    };
}