package com.rjp.eaction.permission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * 申请权限的activity，避免每一个activity都去调用onRequestPermissionsResult
 *
 */
public class PermissionActivity extends Activity {

    public static final int MY_PERMISSION_REQUEST_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= M) {
            boolean b = checkPermissionAllGranted(PermissionUtils.permissions);
            if (b) {
                PermissionUtils.callback.allow();
                finish();
                return;
            }

            int size = PermissionUtils.permissions.size();
            String[] permissions = new String[size];
            for (int i = 0; i < size; i++) {
                permissions[i] = PermissionUtils.permissions.get(i);
            }

            //申请权限
            ActivityCompat.requestPermissions(this, permissions, MY_PERMISSION_REQUEST_CODE);
        }else{
            PermissionUtils.callback.allow();
            finish();
        }
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(List<String> permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSION_REQUEST_CODE) {
            boolean isAllGranted = true;

            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {
                PermissionUtils.callback.allow();
            } else {
                Toast.makeText(this, PermissionUtils.getNotice(), Toast.LENGTH_SHORT).show();
                PermissionUtils.callback.deny();
            }
            //结束透明页面
            finish();
        }
    }

//    Intent intent = new Intent();
//    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//    intent.addCategory(Intent.CATEGORY_DEFAULT);
//    intent.setData(Uri.parse("package:" + getPackageName()));
//    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//    startActivity(intent);
}
