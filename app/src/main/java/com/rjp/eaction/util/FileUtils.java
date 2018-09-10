package com.rjp.eaction.util;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.rjp.eaction.R;

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

    private static File createDirPath(String dirPath, String dirName) {
        File file = new File(dirPath, dirName);
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

    public static File createFilePath(String dirPath, String fileName) {
        File file = new File(dirPath, fileName);
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
     * 存储音乐文件
     * @param context
     * @return
     */
    public static String getMusicDir(Context context) {
        return createDir(getExternalDir(context), "music-cache").getAbsolutePath();
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

    public static String getApkDir(Context context) {
        return createDirPath(getExternalDir(context), "apks").getAbsolutePath();
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
            return FileProvider.getUriForFile(context.getApplicationContext(), context.getString(R.string.file_provider), file);
        } else {
            return Uri.fromFile(file);
        }
    }

    public static String getFilePathByUri(Context context, Uri imageUri) {
        if (context == null || imageUri == null) return null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri)) return imageUri.getLastPathSegment();
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
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
     * 只获取路径，没有实际生成文件
     * @param context
     * @param fileName
     * @return
     */
    public static String getApkFilePathByName(Context context, String fileName) {
        return createFilePath(getApkDir(context), fileName).getAbsolutePath();
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
     *
     * @param context
     * @param name
     * @return
     */
    public static String getAssetsFile(Context context, String name) {
        try {
            InputStream is = context.getAssets().open(name);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String content = new String(buffer, "UTF-8");
            return content;
        } catch (Exception e) {

        }
        return "";
    }
}
