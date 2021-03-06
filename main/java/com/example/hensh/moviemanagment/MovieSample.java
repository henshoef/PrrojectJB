package com.example.hensh.moviemanagment;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Android on 11/03/2018.
 */

public class MovieSample extends android.support.v7.widget.AppCompatButton {
    private  int id;
    private  String name;
    private  String decription;
    private  String imageUrl;


    public MovieSample(Context context, int id, String name, String decription, String imageUrl) {
        super(context);
        this.id = id;
        this.name = name;
        this.decription = decription;
        this.imageUrl = imageUrl;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
