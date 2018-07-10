package com.rjp.eaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rjp.eaction.util.FileUtils;
import com.rjp.eaction.views.pick_photo.PhotoModel;
import com.rjp.eaction.views.pick_photo.PickPhotoView;
import com.rjp.eaction.views.pick_photo.ShowPhotoView;

import java.util.ArrayList;

public class EmptyActivity extends Activity {
    private Activity mContext;
    private PickPhotoView pickPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        mContext = this;

        TextView tvAppId = (TextView) findViewById(R.id.tv_appid);
        tvAppId.setText(BuildConfig.APPLICATION_ID);

        TextView appName = (TextView) findViewById(R.id.tv_appname);
        appName.setText("AppName:" + getResources().getString(R.string.app_name));

        pickPhotoView = (PickPhotoView) findViewById(R.id.pick_photo_view);

        findViewById(R.id.tv_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ShowPhotoView showPhotoView = (ShowPhotoView) findViewById(R.id.show_photo_view);
        ArrayList<PhotoModel> models = new ArrayList<>();
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210689&di=6b94847e26f99309e8d8ae225ecb2a3b&imgtype=0&src=http%3A%2F%2Fimg3.xiazaizhijia.com%2Fwalls%2F20150417%2Fmid_84422024ff063d3.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210688&di=fef5edffe5c8a7c94592ef9fd8629444&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F71%2F22%2F35T58PICrEk_1024.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210687&di=4fb6e7187e93b7e83fb25bf746f2561e&imgtype=0&src=http%3A%2F%2Fpic1.16pic.com%2F00%2F12%2F94%2F16pic_1294464_b.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210687&di=a07f42d66911ed51b1a87105b186d83d&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F13%2F67%2F98B58PICGe8_1024.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210687&di=6fa8a12a2919a83b27d8ec9a13786324&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F13%2F74%2F82%2F40G58PICa2w_1024.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871267380&di=177d3a14c878e0125a839c99f65bed88&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D3564877025%2C796183547%26fm%3D214%26gp%3D0.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210687&di=ac64bc534ff67c8bf75915280b4d3c16&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fd%2F580589c9a4873.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210687&di=00ac515174505055f1975135222095a9&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F12%2F34%2F73C58PIC8uq_1024.jpg"));
        models.add(new PhotoModel(PhotoModel.TYPE_URL,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530871210687&di=081fdd623459424bffbc3f90741f53a9&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F279759ee3d6d55fb945b460267224f4a20a4dd70.jpg"));
        showPhotoView.setPhotoModels(models);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        String filePath = FileUtils.getFilePathByUri(mContext, uri);
        pickPhotoView.addPickPhotoModel(new PhotoModel(PhotoModel.TYPE_FILE, filePath));
    }
}
