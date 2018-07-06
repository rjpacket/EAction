package com.rjp.eaction.views.pick_photo;

/**
 * 选择图片  查看大图  通用的图片model
 * author : Gimpo create on 2018/7/5 12:15
 * email  : jimbo922@163.com
 */
public class PhotoModel {
    public static final int TYPE_FILE = 1;
    public static final int TYPE_URL = 2;

    private String filePath;
    private String imageUrl;

    public PhotoModel(int type, String filePathOrImageUrl) {
        switch (type) {
            case TYPE_FILE:
                this.setFilePath(filePathOrImageUrl);
                break;
            case TYPE_URL:
                this.setImageUrl(filePathOrImageUrl);
                break;
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
