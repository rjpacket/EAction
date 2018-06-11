package com.rjp.eaction.util;

import android.content.Context;
import android.text.TextUtils;

import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * android端上传七牛云，返回ImageUrl到服务器保存数据库  更新图片地址  解决方案
 *
 * 首选上传图片的方案
 *
 * 还可以直接传给后台文件，后台上传七牛云，返回ImageUrl
 * @Author：RJP on 2017/4/1 10:42
 */

public class UploadImageUtils {
    private static UploadImageUtils uploadUtils = null;
    private UploadManager uploadManager;

    private UploadImageUtils() {
        Configuration config = new Configuration.Builder().chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                .connectTimeout(20) // 链接超时。默认 10秒
                .responseTimeout(60) // 服务器响应超时。默认 60秒
                //                .recorder(recorder)  // recorder 分片上传时，已上传片记录器。默认 null
                //                .recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                //                .zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。默认 Zone.zone0
                .build();
        // 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
        uploadManager = new UploadManager(config);
    }

    public static UploadImageUtils getInstance() {
        if(uploadUtils == null){
            synchronized (UploadImageUtils.class){
                if(uploadUtils == null){
                    uploadUtils = new UploadImageUtils();
                }
            }
        }
        return uploadUtils;
    }

    /**
     * 获取七牛token
     *
     * @param mContext
     * @param filePath
     * @param originKey
     * @param callBack
     */
    public void getQiniuUploadToken(Context mContext, final String filePath, final String originKey, final UploadPictureCallBack callBack) {
        new NetUtils.Builder()
                .context(mContext)
                .url("getQiniuToken")
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String model) {
                        String token = "token";
                        String path = "path";
                        uploadPicture(filePath, originKey, token, path, callBack);
                    }

                    @Override
                    public void failure(int code, String msg) {

                    }
                });
    }

    /**
     * 上传picture
     *
     * @param filePath
     * @param originKey
     * @param token
     * @param path
     * @param callBack
     */
    private void uploadPicture(String filePath, String originKey, String token, final String path, final UploadPictureCallBack callBack) {
        UploadOptions uploadOptions = new UploadOptions(null, null, false, new UpProgressHandler() {
            public void progress(String key, double percent) {
                callBack.progress(key, percent);
            }
        }, null);
        uploadManager.put(filePath, originKey, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, com.qiniu.android.http.ResponseInfo info, org.json.JSONObject response) {
                if (info.isOK()) {
                    callBack.complete(path + key);
                } else {
                    callBack.error();
                }
            }
        }, uploadOptions);
    }

    /**
     * 生成图片的名称key
     *
     * 这个key还可以作为tag区别同时的上传请求
     * @param picturePath
     * @return
     */
    public static String getImgKey(String picturePath) {
        String picType;
        String substring = picturePath.substring(picturePath.lastIndexOf("."));
        if (!TextUtils.isEmpty(substring)) {
            picType = substring;
        } else {
            picType = ".jpg";
        }
        return generateImgID() + picType;
    }

    /**
     * 生成图片随机id
     *
     * @return
     */
    public static String generateImgID() {
        return new StringBuilder().append(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())).append(generateThreeRandomNum()).toString();
    }

    /**
     * 生成三位随机数
     *
     * @return
     */
    public static int generateThreeRandomNum() {
        Random random = new Random();
        int num = (int) (random.nextDouble() * (1000 - 100) + 100);
        return num;
    }

    public interface UploadPictureCallBack {
        void progress(String key , double percent);

        void complete(String imageUrl);

        void error();
    }
}
