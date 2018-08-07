package com.rjp.eaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.PopupWindow;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.permission.PermissionCallback;
import com.rjp.eaction.permission.PermissionUtils;
import com.rjp.eaction.ui.fragments.TestFragment;
import com.rjp.eaction.util.FileUtils;
import com.rjp.eaction.util.dialog.DialogUtils;
import com.rjp.eaction.util.dialog.OnDialogClickListener;
import com.rjp.eaction.util.popup.OnPopupBindDataListener;
import com.rjp.eaction.util.popup.PopUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends FragmentActivity {

    private String filePathByName;
    private PopupWindow popupWindow;
    private Context mContext;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        mContext = this;

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

//        ListView listView = findViewById(R.id.list_view);
//        listView.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 20;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if(convertView == null){
//                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_test_activity, null);
//                }
//                CustomTextView ctv1 = convertView.findViewById(R.id.ctv_text1);
//                if(position % 2 == 0){
//                    ctv1.setText("test  ctv");
//                }else {
//                    ctv1.setText("综合分布图双色球走势图-福彩双色球走势图-双色球图表-中彩网图表，从擂台赛数据看,本期彩评师看好的红球向一区和二区倾斜,回顾双色球往期数据,自074期一等奖井喷后,连续6期一等奖开出的注数在个位数,本期继续看好低迷,在近，网易双色球代购平台为您提供双色球机选,双色球复式投注,双色球网上投注等双色球购");
//                }
//                return convertView;
//            }
//        });
        fragments = new ArrayList<>();
        fragments.add(TestFragment.getInstance(10));
        fragments.add(TestFragment.getInstance(5));

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }
        });
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
