package com.exercise.androidproficiencyexercise.data;

/**
 * Created by Akash on 20-05-2018.
 */

public class Rows {

    private String title;

    private String description;

    private String imageHref;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

    @Override
    public String toString() {
        return "ClassPojo [title = " + title + ", description = " + description + ", imageHref = " + imageHref + "]";
    }

}
