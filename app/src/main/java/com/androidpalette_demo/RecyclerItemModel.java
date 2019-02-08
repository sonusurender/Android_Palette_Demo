package com.androidpalette_demo;

/**
 * Created by sonu on 27/03/17.
 */


/* Model for Grid Item  */
public class RecyclerItemModel {
    private String label, imageUrl;


    public RecyclerItemModel(String label, String imageUrl) {
        this.label = label;
        this.imageUrl = imageUrl;
    }

    public String getLabel() {
        return label;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
