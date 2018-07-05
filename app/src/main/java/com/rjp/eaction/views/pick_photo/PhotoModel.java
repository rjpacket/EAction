package com.rjp.eaction.views.pick_photo;

/**
 *
 * 选择图片  查看大图  通用的图片model
 * author : Gimpo create on 2018/7/5 12:15
 * email  : jimbo922@163.com
 */
public class PhotoModel {
    private String filePath;

    public PhotoModel(String filePath){
        this.setFilePath(filePath);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
