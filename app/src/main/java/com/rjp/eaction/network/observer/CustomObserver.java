package com.rjp.eaction.network.observer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.rjp.eaction.network.exception.ExceptionHandle;
import com.rjp.eaction.network.loading.LoadingDialog;
import com.rjp.eaction.network.util.NetworkUtil;

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

    public CustomObserver(Context context, boolean isShowLoading){
        this.mContext = context;
        this.isShowLoading = isShowLoading;
        if(isShowLoading){
            loadingDialog = new LoadingDialog();
            loadingDialog.initDialog(mContext, "");
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (!NetworkUtil.isNetworkAvailable(mContext)) {
            Toast.makeText(mContext, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show();
            onComplete();
        }
        //上游开始
        loadingDialog.show();
    }

    @Override
    public void onComplete() {
        //接收完成
        loadingDialog.close();
    }

    //以下两个方法需要暴漏出去

    @Override
    public void onError(Throwable e) {
        Log.e("RJP", e.getMessage());

        if(e instanceof ExceptionHandle.ResponeThrowable){
            onError((ExceptionHandle.ResponeThrowable)e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }

    public abstract void onError(ExceptionHandle.ResponeThrowable e);
}