package com.rjp.eaction.function;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.rjp.eaction.R;
import com.rjp.eaction.permission.PermissionCallback;
import com.rjp.eaction.permission.PermissionUtils;
import com.rjp.eaction.util.FileUtils;
import com.rjp.eaction.util.ImageUtils;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * @author Gimpo create on 2017/5/26 20:25
 * @email : jimbo922@163.com
 */

public class SelectPhotoPoupWindow extends PopupWindow implements View.OnClickListener{
    public static final int NO_LAYOUT = -1;
    private static final int MY_TAKE_PICTURE = 1001;
    private static final int MY_PICK_PICTURE = 1002;
    private static final int MY_CROP_PICTURE = 1003;
    private Activity mActivity;
    private OnSelectPhotoListener mListener;
    private File cropFile;

    public SelectPhotoPoupWindow(Activity context, int layoutId) {
        mActivity = context;
        if (layoutId == NO_LAYOUT) {
            layoutId = R.layout.popup_window_choose_photo;
        }
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        setContentView(view);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setTouchable(true);
        setFocusable(true);
        setOutsideTouchable(true);
        ColorDrawable drawable = new ColorDrawable();
        setBackgroundDrawable(drawable);
        initView(view);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                setWindowAlpha(1.0f);
            }
        });
    }

    private void initView(View view) {
        Button btnTakePhoto = (Button) view.findViewById(R.id.btn_take_photo);
        btnTakePhoto.setOnClickListener(this);
        Button btnPickPhoto = (Button) view.findViewById(R.id.btn_pick_photo);
        btnPickPhoto.setOnClickListener(this);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
    }

    public void show(View parentView, int gravity) {
        if (!isShowing()) {
            setWindowAlpha(0.5f);
            showAtLocation(parentView, gravity, 0, 0);
        }
    }

    public void setWindowAlpha(float alpha) {
        Window window = mActivity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = alpha;
        window.setAttributes(attributes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pick_photo:
                new PermissionUtils.Builder()
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .context(mActivity)
                        .build()
                        .request(new PermissionCallback() {
                            @Override
                            public void allow() {
                                pickPhoto();
                            }

                            @Override
                            public void deny() {

                            }
                        });
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_take_photo:
                new PermissionUtils.Builder()
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .permission(Manifest.permission.CAMERA)
                        .context(mActivity)
                        .build()
                        .request(new PermissionCallback() {
                            @Override
                            public void allow() {
                                takePhoto();
                            }

                            @Override
                            public void deny() {

                            }
                        });
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = null;
        switch (requestCode) {
            case MY_TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    String path = FileUtils.getCacheImageDir(mActivity);
                    String name = "output.png";
                    File camerafile = new File(path,name);
                    Uri imageUri = FileUtils.getUriByFile(mActivity, camerafile);
                    //设置裁剪之后的图片路径文件
                    cropFile = FileUtils.getImageFileByName(mActivity, "cutcamera.png");
                    mActivity.startActivityForResult(ImageUtils.cropImage(imageUri, cropFile), MY_CROP_PICTURE);
                }
                break;
            case MY_PICK_PICTURE:
                if (data == null) {
                    return;
                }
                if (resultCode == RESULT_OK) {
                    //设置裁剪之后的图片路径文件
                    cropFile = FileUtils.getImageFileByName(mActivity, "cutcamera.png");
                    mActivity.startActivityForResult(ImageUtils.cropImage(data.getData(), cropFile), MY_CROP_PICTURE);
                }
                break;
            case MY_CROP_PICTURE:  // 统一裁剪后处理
                if (resultCode == RESULT_OK) {
                    if (mListener != null) {
                        mListener.onSelectOnePhoto(cropFile.getAbsolutePath());
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        //创建一个file，用来存储拍照后的照片
        File outputfile = FileUtils.getCacheImageFileByName(mActivity, "output.png");
        Uri imageuri = FileUtils.getUriByFile(mActivity, outputfile);
        //启动相机程序
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageuri);
        mActivity.startActivityForResult(intent, MY_TAKE_PICTURE);
        dismiss();
    }

    /**
     * 选择照片
     */
    private void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        mActivity.startActivityForResult(intent, MY_PICK_PICTURE);
        dismiss();
    }

    public void setOnSelectPhotoListener(OnSelectPhotoListener mListener) {
        this.mListener = mListener;
    }

    public interface OnSelectPhotoListener {
        void onSelectOnePhoto(String filePath);
    }
}
