package com.rjp.eaction.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * author : Gimpo create on 2018/7/11 12:44
 * email  : jimbo922@163.com
 */
public class ToastUtils {

    private static ToastUtils ourInstance = null;
    private Context context;
    private String msg;
    private int duration;
    private int gravity;
    private int xOffset;
    private int yOffset;
    private View view;
    private boolean showType;

    private static ToastUtils getInstance() {
        if (ourInstance == null) {
            synchronized (ToastUtils.class) {
                if (ourInstance == null) {
                    ourInstance = new ToastUtils();
                }
            }
        }
        return ourInstance;
    }

    private ToastUtils() {

    }

    public static class Builder {
        private Context context;
        private String msg;
        private int duration;
        private int gravity;
        private int xOffset;
        private int yOffset;
        private View view;

        public Builder() {

        }

        public ToastUtils.Builder context(Context context) {
            this.context = context;
            return this;
        }

        public ToastUtils.Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public ToastUtils.Builder duration(int duration) {
            this.duration = duration;
            return this;
        }

        public ToastUtils.Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public ToastUtils.Builder xOffset(int xOffset) {
            this.xOffset = xOffset;
            return this;
        }

        public ToastUtils.Builder yOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public ToastUtils.Builder contentView(View view) {
            this.view = view;
            return this;
        }

        public ToastUtils build() {
            ToastUtils toastUtils = ToastUtils.getInstance();
            if (this.context == null) {
                throw new IllegalArgumentException("context must be not null");
            }
            toastUtils.context = this.context;
            toastUtils.msg = this.msg;
            toastUtils.duration = this.duration;
            toastUtils.gravity = this.gravity;
            toastUtils.xOffset = this.xOffset;
            toastUtils.yOffset = this.yOffset;
            toastUtils.view = this.view;
            return toastUtils;
        }
    }

    private static Toast mToast = null;

    public void show() {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, duration);
        } else {
            mToast.setText(msg);
        }
        mToast.setDuration(duration);
        if(gravity != 0){
            mToast.setGravity(gravity, xOffset, yOffset);
        }
        if(view != null) {
            mToast.setView(view);
        }
        mToast.show();
    }

    /**
     * 简单的调用
     * @param mContext
     * @param msg
     */
    public static void showToast(Context mContext, String msg){
        new Builder()
                .context(mContext)
                .msg(msg)
                .build()
                .show();
    }
}
