package com.rjp.eaction.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.rjp.eaction.BuildConfig;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * 辅助工具
 * author : Gimpo create on 2018/5/24 18:02
 * email  : jimbo922@163.com
 */
public class AppUtils {

    /**
     * dp 转化成 px
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * dp 转化成 px
     * @param px
     * @return
     */
    public static int px2dp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 获取手机的imei  也就是设备号
     * @return
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMEI(Context context){
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = null;
            if (telephonyManager != null) imei = telephonyManager.getDeviceId();
            if (TextUtils.isEmpty(imei)) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取手机系统版本号
     * @return
     */
    public static String getOSVersion(){
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机厂商
     * @return
     */
    public static String getBrand(){
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getModel(){
        return Build.MODEL;
    }

    /**
     * 获取app的版本号
     * @return
     */
    public static String getAppVersion(){
        return BuildConfig.VERSION_NAME;
    }

    /**
     * 获取appId
     * @return
     */
    public static String getAppId(){
        return BuildConfig.APPLICATION_ID;
    }

    /**
     * 获取手机 mac 地址
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getMacAddress(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // 如果当前设备系统大于等于6.0 使用下面的方法
            //以下第一种获取方法  根据wifi获取mac地址
            try {
                List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
                for (NetworkInterface nif : all) {
                    if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:",b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            } catch (Exception ex) {

            }
            return "";
        } else {
            //6.0以下获取mac地址
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                // 获取MAC地址
                WifiInfo wifiInfo = null;
                String mac = "";
                if (wifiManager != null) {
                    wifiInfo = wifiManager.getConnectionInfo();
                    mac = wifiInfo.getMacAddress();
                }
                if (TextUtils.isEmpty(mac)) {
                    mac = "";
                }
                return mac;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    /**
     * 获取渠道
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        String agent;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            agent = appInfo.metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            agent = "channel_test";
        }
        return agent;
    }

    /**
     * 浏览器查看url
     * @param context
     * @param url
     */
    public static void browserCheck(Context context, String url){
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
