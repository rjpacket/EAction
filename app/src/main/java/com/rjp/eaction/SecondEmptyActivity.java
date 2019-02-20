package com.rjp.eaction;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rjp.eaction.web.PopLayerActivity;

public class SecondEmptyActivity extends Activity {

    private Context mContext;
    private ValueAnimator animator1;

    public static void trendTo(Context mContext) {
        Intent intent = new Intent(mContext, SecondEmptyActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_empty);

        mContext = this;

        animator1 = ValueAnimator.ofInt(0, 1);
        animator1.setDuration(3000);
        animator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                PopLayerActivity.start(mContext, "https://m.dajiang365.com//videoPop/analystPop.html");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ValueAnimator animator = ValueAnimator.ofInt(0, 1);
        animator.setDuration(2000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                PopLayerActivity.start(mContext, "https://m.dajiang365.com//videoPop/downloadQiuliao.html");
                animator1.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
