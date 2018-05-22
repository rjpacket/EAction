package com.rjp.eaction.network.loading;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjp.eaction.R;

/**
 * 加载框
 */
public class LoadingDialog {
    LVCircularRing mLoadingView;
    Dialog dialog;

    public LoadingDialog() {

    }

    public void initDialog(Context context, String msg) {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, null);

        // 页面中的LoadingView
        mLoadingView = (LVCircularRing) view.findViewById(R.id.lv_circularring);
        // 页面中显示文本
        TextView loadingText = (TextView) view.findViewById(R.id.loading_text);
        if (TextUtils.isEmpty(msg)) {
            msg = "请稍候...";
        }
        // 显示文本
        loadingText.setText(msg);
        // 创建自定义样式的Dialog
        dialog = new Dialog(context, R.style.loading_dialog);
        // 设置返回键无效
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 显示一个dialog
     */
    public void show() {
        if (!dialog.isShowing()) {
            dialog.show();
            mLoadingView.startAnim();
        }
    }

    /**
     * 关闭加载
     */
    public void close() {
        try {
            if (dialog != null && dialog.isShowing()) {
                mLoadingView.stopAnim();
                dialog.dismiss();
                mLoadingView = null;
                dialog = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}