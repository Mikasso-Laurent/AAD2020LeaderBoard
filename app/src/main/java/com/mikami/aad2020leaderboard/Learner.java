package com.mikami.aad2020leaderboard;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class Learner {
    public String name;
    public int hour;
    public int score;
    public String country;
    public String badgeUrl;

    public Learner(String name, int hour, int score, String country, String badgeUrl) {
        this.name = name;
        this.hour = hour;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

}
