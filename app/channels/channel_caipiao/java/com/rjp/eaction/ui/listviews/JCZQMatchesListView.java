package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.BaseAdapter;

import com.rjp.eaction.bean.JCZQMatchModel;
import com.rjp.eaction.views.base_listview.LoadMoreListView;
import com.rjp.eaction.views.base_listview.RefreshListView;

/**
 * Created by small on 2018/7/25.
 */

public class JCZQMatchesListView extends RefreshListView<JCZQMatchModel> {
    public JCZQMatchesListView(Context context) {
        super(context);
    }

    public JCZQMatchesListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return null;
    }

    @Override
    protected void requestData() {

    }
}
