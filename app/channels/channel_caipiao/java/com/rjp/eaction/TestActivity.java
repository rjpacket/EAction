package com.rjp.eaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.permission.PermissionCallback;
import com.rjp.eaction.permission.PermissionUtils;
import com.rjp.eaction.util.FileUtils;
import com.rjp.eaction.util.dialog.DialogUtils;
import com.rjp.eaction.util.dialog.OnDialogClickListener;
import com.rjp.eaction.util.popup.OnPopupBindDataListener;
import com.rjp.eaction.util.popup.PopUtils;
import com.rjp.eaction.views.base_listview.RefreshImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends FragmentActivity {

    private String filePathByName;
    private PopupWindow popupWindow;
    private Context mContext;
    private List<Fragment> fragments;
    private RefreshImageView ivMaoYan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        mContext = this;

        ivMaoYan = findViewById(R.id.iv_maoyan);
        ivMaoYan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivMaoYan.startAnim();
            }
        });

        SeekBar seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ivMaoYan.setProgress((float) (progress * 1.0 / 100));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivMaoYan.startAnim();
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivMaoYan.stopAnim();
            }
        });

        FileDownloader.setup(this);

        new PermissionUtils.Builder().context(this).permission(Manifest.permission.WRITE_EXTERNAL_STORAGE).build().request(new PermissionCallback() {
            @Override
            public void allow() {
//                showUpdateDialog();
            }

            @Override
            public void deny() {

            }
        });

        List<String> models = new ArrayList<>();
        models.add("123");
        popupWindow = new PopUtils.Builder<String>().context(this).width(100).models(models).bindData(new OnPopupBindDataListener<String>() {
            @Override
            public void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.tv_option, item);

            }
        }).build().show();
    }

    private void showUpdateDialog() {
        new DialogUtils.Builder().context(this).notice("有新的更新内容，是否更新到最新版本？").title("更新").onClickListener(new OnDialogClickListener() {
            @Override
            public void onConfirm() {
                downloadAPK("http://imtt.dd.qq.com/16891/094A1B416A1C49DC44F039C1FD79C733.apk?fsname=aolei.fc_5.6.3_58.apk&amp;csr=1bbd");
            }

            @Override
            public void onCancel() {

            }
        }).build().show();
    }

    private void downloadAPK(String apkUrl) {
//        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
//        //设置下载的文件存储的地址，我们这里将下载的apk文件存在/Download目录下面
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "caipiao.apk");
//        //设置现在的文件可以被MediaScanner扫描到。
//        request.allowScanningByMediaScanner();
//        request.setAllowedNetworkTypes(NETWORK_WIFI);
//        //设置通知的标题
//        request.setTitle(getString(R.string.app_name));
//        //设置下载的时候Notification的可见性。
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        //设置下载文件类型
//        request.setMimeType("application/vnd.android.package-archive");
//        long enqueueId = downloadManager.enqueue(request);

        filePathByName = FileUtils.getApkFilePathByName(this, "caipiao.apk");
        FileDownloader.getImpl().create(apkUrl).setPath(filePathByName).setListener(new FileDownloadSampleListener() {
            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void completed(BaseDownloadTask task) {
//                        requestInstallPermission();
                installApk(filePathByName);
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
            }
        }).start();
    }

    private void requestInstallPermission() {
        new PermissionUtils.Builder().context(this).permission(Manifest.permission.REQUEST_INSTALL_PACKAGES).build().request(new PermissionCallback() {
            @Override
            public void allow() {
                installApk(filePathByName);
            }

            @Override
            public void deny() {
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                startActivityForResult(intent, 1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            installApk(filePathByName);
        }
    }

    private void installApk(String filePath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(FileUtils.getUriByFile(this, new File(filePath)), "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
