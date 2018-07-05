package com.rjp.eaction.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * author : Gimpo create on 2018/5/25 14:22
 * email  : jimbo922@163.com
 */
public class ImageUtils {

    /**
     * 加载一张图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void load(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    /**
     * 裁剪图片
     *
     * @param imageUri 需要被裁剪的图片源
     * @return
     */
    public static Intent cropImage(Uri imageUri, File cropFile) {
        try {
            //直接裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            Uri outputUri = Uri.fromFile(cropFile);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", AppUtils.dp2px(100));
            intent.putExtra("outputY", AppUtils.dp2px(100));
            intent.putExtra("scale", true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            return intent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Bitmap缩放，注意源Bitmap在缩放后将会被回收
     *
     * @param origin
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getScaleBitmap(Bitmap origin, int width, int height) {
        float originWidth = origin.getWidth();
        float originHeight = origin.getHeight();

        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width) / originWidth;
        float scaleHeight = ((float) height) / originHeight;

        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap scale = Bitmap.createBitmap(origin, 0, 0, (int) originWidth, (int) originHeight, matrix, true);
        origin.recycle();
        return scale;
    }

    /**
     * 从View获取Bitmap
     *
     * @param view View
     * @return Bitmap
     */
    public static Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        view.draw(canvas);
        return bitmap;
    }

    /**
     * 选择照片
     */
    public static void pickPhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }
}
