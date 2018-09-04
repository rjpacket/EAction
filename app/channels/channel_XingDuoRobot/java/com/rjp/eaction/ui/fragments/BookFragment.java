package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.bean.HomeBannerModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.views.SearchLabelView;
import com.rjp.eaction.util.AppUtils;

import butterknife.BindView;

import java.util.List;

import static com.rjp.eaction.utils.SPUtils.USER_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment {
    @BindView(R.id.book_search_view)
    SearchLabelView bookSearchView;

    public BookFragment() {
        // Required empty public constructor
    }

    public static BookFragment getInstance(){
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_book;
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "";
    }

    @Override
    protected void handle() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bookSearchView.getLayoutParams();
        params.setMargins(0, AppUtils.getStatusBarHeight(mContext), 0, 0);

        getBookList();
    }

    private void getBookList() {
        new NetUtils.Builder()
                .url("book/findAll.jhtml")
                .context(mContext)
                .build()
                .model(new ResponseCallback<String>() {
                    @Override
                    public void success(String models) {
                    }

                    @Override
                    public void failure(String code, String msg) {

                    }
                });
    }
}
