package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;
import com.rjp.eaction.util.AppUtils;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment {
    @BindView(R.id.ll_book_search_label)
    LinearLayout llBookSearchLabel;

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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) llBookSearchLabel.getLayoutParams();
        params.setMargins(AppUtils.dp2px(10), AppUtils.getStatusBarHeight(mContext) + AppUtils.dp2px(10), AppUtils.dp2px(10), AppUtils.dp2px(10));
    }
}
