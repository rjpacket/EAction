(本文仅代表个人观点，如果不对，请留言指正)

最近在看新的网络请求框架retrofit，看到他支持链式调用，也就是我们知道的构建者模式。好像很多开源的库都热衷于构建者模式，究竟是跟风还是确实有什么独特的好处呢？

我们通过一个案例体会一下他的好处。

### 案例一、提示弹框

开发过程中，我们经常需要弹出一个提示框：

这个弹框有标题、提示内容、取消按钮、确认按钮等，如果不使用构建者模式，我之前的封装是这样的：
```
public class NoticeDialogUtils {

    /**
     * 只响应一个按钮的dialog
     * @param context
     * @param msg
     */
    public static NoticeDialog notice0(Context context , String msg ){
        return notice(context, "提醒", msg, null, "确定", true, null, new NoticeDialog.OnNoticeClickListener() {
            @Override
            public void onNoticeClick(NoticeDialog dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    /**
     * 只响应一个按钮的dialog
     * @param context
     * @param msg
     * @param listener
     */
    public static NoticeDialog notice1(Context context , String msg , NoticeDialog.OnNoticeClickListener listener){
        return notice(context, "提醒", msg, "取消", "确定", true, new NoticeDialog.OnNoticeClickListener() {
            @Override
            public void onNoticeClick(NoticeDialog dialog, int which) {
                dialog.dismiss();
            }
        }, listener);
    }


    /**
     * 只响应一个按钮的dialog  点击外部不可以取消
     * @param context
     * @param msg
     * @param listener
     */
    public static NoticeDialog notice1Cancel(Context context , String msg , boolean cancel,  NoticeDialog.OnNoticeClickListener listener){
        return notice(context, "提醒", msg, "取消", "确定", cancel, new NoticeDialog.OnNoticeClickListener() {
            @Override
            public void onNoticeClick(NoticeDialog dialog, int which) {
                dialog.dismiss();
            }
        }, listener);
    }

    /**
     * 响应两个按钮的dialog , 取消按钮除了dismiss()会执行操作
     * @param context
     * @param msg
     * @param listener
     */
    public static NoticeDialog notice2(Context context , String msg , NoticeDialog.OnNoticeClickListener listener){
        return notice(context, "提醒", msg, "取消", "确定", listener);
    }

    /**
     * 提示的dialog
     * @param context
     * @param title
     * @param msg
     * @param cancel
     * @param confirm
     * @param listener
     */
    public static NoticeDialog notice(Context context , String title , String msg , String cancel , String confirm , NoticeDialog.OnNoticeClickListener listener){
        return notice(context, title, msg, cancel, confirm, true, listener , listener);
    }

    /**
     * 提示的dialog  可以取消
     * @param context
     * @param title
     * @param msg
     * @param cancel
     * @param confirm
     * @param listener
     */
    public static NoticeDialog noticeCancel(Context context , String title , String msg , String cancel , String confirm , boolean cancelble, NoticeDialog.OnNoticeClickListener listener){
        return notice(context, title, msg, cancel, confirm, cancelble, listener , listener);
    }

    /**
     * 提示dialog
     * @param context
     * @param title
     * @param msg
     * @param cancel
     * @param confirm
     * @param cancelListener
     * @param confirmListener
     */
    private static NoticeDialog notice(Context context , String title , String msg , String cancel , String confirm ,boolean cancelble,
                              NoticeDialog.OnNoticeClickListener cancelListener , NoticeDialog.OnNoticeClickListener confirmListener){
        NoticeDialog.Builder builder = new NoticeDialog.Builder(context);
        NoticeDialog dialog = builder.setTitle(title)
                .setOutSideCancelble(cancelble)
                .setMessage(msg)
                .setPositiveButton(confirm, confirmListener)
                .setNegativeButton(cancel, cancelListener)
                .create();
        dialog.show();
        return dialog;
    }
}
```

然后调用的地方补充参数：
```
NoticeDialogUtils.notice(mContext, "提醒", "返回添加赛事，将放弃购买此保存订单", "取消", "确定", new NoticeDialog.OnNoticeClickListener() {
            @Override
            public void onNoticeClick(NoticeDialog dialog, int which) {
                switch (which) {
                    case NOTICE_CONFIRM:
                        dialog.dismiss();
                        onAddMatchClick();
                        break;
                    case NOTICE_CANCEL:
                        dialog.dismiss();
                        break;
                }
            }
        });
```

代码很简洁，只是传参的时候需要仔细对照api，可读性不太友好。

实际上，这样写已经算合格了，没有在需要弹框的时候去复制粘贴上一个弹框的代码。那么还能不能优化呢？我们看看利用构建者模式封装会是什么情况：
```
public class DialogUtils {

    public static DialogUtils instance;

    public static DialogUtils getInstance() {
        if (instance == null) {
            synchronized (DialogUtils.class) {
                if (instance == null) {
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    public static class Builder {
        private Context context;
        private View layout;
        private int width;
        private int gravity;
        private int anim;
        private String title = "提示";
        private String notice = "";
        private String confirm = "确定";
        private String cancel = "取消";
        private boolean outsideCancel = false;
        private View.OnClickListener onClickListener;

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder layout(View layout) {
            this.layout = layout;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder anim(int anim) {
            this.anim = anim;
            return this;
        }

        public Builder outsideCancel(boolean outsideCancel) {
            this.outsideCancel = outsideCancel;
            return this;
        }

        public Builder onClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder notice(String notice) {
            this.notice = notice;
            return this;
        }

        public Builder confirm(String confirm) {
            this.confirm = confirm;
            return this;
        }

        public Builder cancel(String cancel) {
            this.cancel = cancel;
            return this;
        }

        public DialogUtils build() {
            DialogUtils dialogUtils = DialogUtils.getInstance();
            if (context == null) {
                throw new IllegalArgumentException("context must be not null");
            }
            dialogUtils.context = this.context;
            if (layout == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                this.layout = inflater.inflate(R.layout.dialog_common_dialog_layout, null);
                dialogUtils.isUseDefaultLayout = true;
            }
            dialogUtils.layout = this.layout;
            if (width == 0) {
                width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.8);
            }
            dialogUtils.width = this.width;
            if (gravity == 0) {
                gravity = Gravity.CENTER;
            }
            dialogUtils.gravity = this.gravity;
            if(anim == 0){
                anim = R.style.dialog_default_anim;
            }
            dialogUtils.anim = this.anim;
            dialogUtils.title = this.title;
            dialogUtils.notice = this.notice;
            dialogUtils.confirm = this.confirm;
            dialogUtils.cancel = this.cancel;
            dialogUtils.outsideCancel = this.outsideCancel;
            dialogUtils.onClickListener = this.onClickListener;
            return dialogUtils;
        }
    }

    private Context context;
    private View layout;
    private int width;
    private int gravity;
    private int anim;
    private String title;
    private String notice;
    private String confirm;
    private String cancel;
    private boolean outsideCancel;
    private View.OnClickListener onClickListener;
    //是否使用默认的布局
    private boolean isUseDefaultLayout;

    public Dialog show() {
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setContentView(layout);
        dialog.setCanceledOnTouchOutside(outsideCancel);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getAttributes().width = width;
            window.setGravity(gravity);
            window.setWindowAnimations(anim);
        }
        if (isUseDefaultLayout) {
            TextView tvTitle = layout.findViewById(R.id.tv_title);
            TextView tvNotice = layout.findViewById(R.id.tv_notice);
            TextView tvCancel = layout.findViewById(R.id.tv_cancel);
            TextView tvConfirm = layout.findViewById(R.id.tv_confirm);
            tvTitle.setText(title);
            tvNotice.setText(notice);
            tvCancel.setText(cancel);
            tvConfirm.setText(confirm);
            tvConfirm.setOnClickListener(onClickListener);
            tvCancel.setOnClickListener(onClickListener);
        }
        dialog.show();
        return dialog;
    }
}
```

然后是调用的时候：
```
    new DialogUtils.Builder()
                .context(MainActivity.this)
                .title("更新")
                .notice("是否更新到最新版本？")
                .onClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .build()
                .show();
```
直观上，可读性非常强，api很灵活，参数之间可以随机组合。

### 疑问
通过案例我们可以得出一个结论，构建者的api很优雅，但是build()这个方法是不是必要的呢？
我们也可以直接在外部内return this;为什么一定要增加一个内部类呢？
如下的写法：
```
public class Out {

    private String param;

    public Out param(String param){
        this.param = param;
        return this;
    }

    public static class Inner{
        private String param;

        public Inner param(String param){
            this.param = param;
            return this;
        }

        public Out build(){
            Out out = new Out();
            out.param = param;
            return out;
        }
    }

    public void show(){

    }
}
```

```

    new Out.Inner().param("123").build();
    new Out().param("123");

```
我们上下两种方式都是可以传入参数的。那么这个build()方法是不是很多余呢？
自然是不多余。下面的写法在任何一次调用之后都是能拿到Out这个对象进行最终的方法show()的调用，但这是很危险的，因为参数还没有确定完全准备好，很可能发生空指针异常。
所以build()这个方法就可以进行风险规避，在每一个参数引用之前进行判断拦截：
```
    public DialogUtils build() {
            DialogUtils dialogUtils = DialogUtils.getInstance();
            if (context == null) {
                throw new IllegalArgumentException("context must be not null");
            }
            dialogUtils.context = this.context;
            if (layout == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                this.layout = inflater.inflate(R.layout.dialog_common_dialog_layout, null);
                dialogUtils.isUseDefaultLayout = true;
            }
            dialogUtils.layout = this.layout;
            if (width == 0) {
                width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.8);
            }
            dialogUtils.width = this.width;
            if (gravity == 0) {
                gravity = Gravity.CENTER;
            }
            dialogUtils.gravity = this.gravity;
            if(anim == 0){
                anim = R.style.dialog_default_anim;
            }
            dialogUtils.anim = this.anim;
            dialogUtils.title = this.title;
            dialogUtils.notice = this.notice;
            dialogUtils.confirm = this.confirm;
            dialogUtils.cancel = this.cancel;
            dialogUtils.outsideCancel = this.outsideCancel;
            dialogUtils.onClickListener = this.onClickListener;
            return dialogUtils;
        }
```
这就保证了代码的健壮性。

### 案例二、网络请求库的封装
正如上面提到的构建者的好处，所以我相信retrofit使用构建者肯定是考虑到了这一点，因为网络请求的时候参数的变化实在是太多了，如果使用传统的方法传参，将是一场灾难。所以赶紧改造自己的网络请求库吧。

代码比较少，不传github了。

