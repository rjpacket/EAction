package com.rjp.eaction.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.InputStream;

import static android.os.Build.VERSION_CODES.N;

/**
 * author : Gimpo create on 2018/6/7 12:26
 * email  : jimbo922@163.com
 */
public class FileUtils {

    /**
     * 在目录下创建目录
     *
     * @param dirPath
     * @param dirName
     * @return
     */
    private static File createDir(String dirPath, String dirName) {
        File file = new File(dirPath, dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 新建一个文件
     *
     * @param dirPath
     * @param fileName
     * @return
     */
    public static File createFile(String dirPath, String fileName) {
        File file = new File(dirPath, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 本应用所有的文件存储的根目录
     *
     * @param context
     * @return
     */
    private static String getExternalDir(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = Environment.getExternalStorageDirectory();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                return cacheDir.getAbsolutePath() + File.separator + "EAction";
            }
        }
        return getCacheDir(context);
    }

    /**
     * 本应用外部缓存目录
     *
     * @param context
     * @return
     */
    private static String getCacheDir(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            return context.getCacheDir().getAbsolutePath() + File.separator + "EAction";
        }
        return externalCacheDir.getAbsolutePath() + File.separator + "EAction";
    }

    /**
     * 获取 存储图片 的路径
     *
     * @param context
     * @return
     */
    public static String getImageDir(Context context) {
        return createDir(getExternalDir(context), "images").getAbsolutePath();
    }

    /**
     * 获取 存储图片 的缓存文件路径
     *
     * @param context
     * @return
     */
    public static String getCacheImageDir(Context context) {
        return createDir(getCacheDir(context), "images").getAbsolutePath();
    }


    /**
     * 7.0以上需要处理  将file://开头的资源转成content://开头的
     *
     * @param file
     * @return
     */
    public static Uri getUriByFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= N) {
            return FileProvider.getUriForFile(context.getApplicationContext(), "com.rjp.eaction.fileprovider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

    /**
     * 生成一个内部存储图片文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static File getImageFileByName(Context context, String fileName) {
        return createFile(getImageDir(context), fileName);
    }

    /**
     * 生成一个缓存目录图片文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static File getCacheImageFileByName(Context context, String fileName) {
        return createFile(getCacheImageDir(context), fileName);
    }

    /**
     * 获取assets下的文件
     * @param context
     * @param name
     * @return
     */
    public static String getAssetsFile(Context context, String name){
        try {
            InputStream is = context.getAssets().open(name);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String content = new String(buffer, "UTF-8");
            return content;
        }catch (Exception e){

        }
        return "";
    }
}
