package com.rjp.eaction.network.observer;

import android.content.Context;
import android.util.Log;

import com.rjp.eaction.network.cancel.RxApiManager;
import com.rjp.eaction.network.exception.ExceptionHandle;
import com.rjp.eaction.network.loading.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 下游接收者
 * author : Gimpo create on 2018/5/15 19:42
 * email  : jimbo922@163.com
 */
public abstract class CustomObserver<T> implements Observer<T> {
    //等待的加载框
    private LoadingDialog loadingDialog;

    private Context mContext;
    private boolean isShowLoading;

    //缓存请求的tag
    private String tag;

    public CustomObserver(Context context, boolean isShowLoading, String tag){
        this.mContext = context;
        this.isShowLoading = isShowLoading;
        this.tag = tag;
        if(isShowLoading){
            loadingDialog = new LoadingDialog();
            loadingDialog.initDialog(mContext, "");
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        //将请求记录
        RxApiManager.get().add(tag, d);

        //上游开始
        if(loadingDialog != null && isShowLoading) {
            loadingDialog.show();
        }

//        if (!NetworkUtil.isNetworkAvailable(mContext)) {
//            Toast.makeText(mContext, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show();
//            onComplete();
//        }
    }

    @Override
    public void onComplete() {
        //接收完成
        if(loadingDialog != null && isShowLoading) {
            loadingDialog.close();
        }
    }

    //以下两个方法需要暴漏出去

    @Override
    public void onError(Throwable e) {
        Log.e("请求异常——下游接收不到数据", e.getMessage());
        onError(ExceptionHandle.handleException(e));
        //异常 下游接收不到数据
        if(loadingDialog != null && isShowLoading) {
            loadingDialog.close();
        }
    }

    public abstract void onError(ExceptionHandle.ResponeThrowable e);
}
