package com.rjp.eaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rjp.eaction.ui.views.RatingCircleImageView;

public class TestActivity extends Activity {

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, TestActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RatingCircleImageView imageView = (RatingCircleImageView) findViewById(R.id.image_view);
        imageView.setRating(0.5f);
    }
}
