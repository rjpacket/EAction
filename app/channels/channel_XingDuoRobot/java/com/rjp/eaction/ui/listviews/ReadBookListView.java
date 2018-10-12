package com.rjp.eaction.ui.listviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.*;

import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.bean.ReadbookModel;
import com.rjp.eaction.network.NetUtils;
import com.rjp.eaction.network.UrlConst;
import com.rjp.eaction.network.callback.ResponseCallback;
import com.rjp.eaction.ui.activitys.BookDetailActivity;
import com.rjp.eaction.ui.activitys.PlayBookActivity;
import com.rjp.eaction.util.ImageUtils;
import com.rjp.eaction.utils.SPUtils;
import com.rjp.eaction.views.base_listview.LoadMoreListView;
import com.rjp.eaction.views.base_listview.RefreshListView;

import java.util.List;

import static com.rjp.eaction.network.UrlConst.URL_BOOK_LIST;

/**
 * Created by small on 2018/8/21.
 */

public class ReadBookListView extends RefreshListView<ReadbookModel> {
    public ReadBookListView(Context context) {
        super(context);
    }

    public ReadBookListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected BaseAdapter getListAdapter() {
        return new CommonAdapter<ReadbookModel>(mContext, R.layout.item_read_book_list_view, mDatas) {
            @Override
            protected void convert(ViewHolder viewHolder, ReadbookModel model, int position) {
                ReadbookModel leftBook = null;
                ReadbookModel middleBook = null;
                ReadbookModel rightBook = null;
                int count = getCount();
                if (position == count - 1) {
                    if (count % 3 == 1) {
                        leftBook = mDatas.get(3 * position);
                    } else if (count % 3 == 2) {
                        leftBook = mDatas.get(3 * position);
                        middleBook = mDatas.get(3 * position + 1);
                    } else {
                        leftBook = mDatas.get(3 * position);
                        middleBook = mDatas.get(3 * position + 1);
                        rightBook = mDatas.get(3 * position + 2);
                    }
                } else {
                    leftBook = mDatas.get(3 * position);
                    middleBook = mDatas.get(3 * position + 1);
                    rightBook = mDatas.get(3 * position + 2);
                }
                LinearLayout llLeftLabel = viewHolder.getView(R.id.ll_left_label);
                LinearLayout llMiddleLabel = viewHolder.getView(R.id.ll_middle_label);
                LinearLayout llRightLabel = viewHolder.getView(R.id.ll_right_label);

                ImageView ivLeftBook = viewHolder.getView(R.id.iv_book_left);
                ImageView ivMiddleBook = viewHolder.getView(R.id.iv_book_middle);
                ImageView ivRightBook = viewHolder.getView(R.id.iv_book_right);
                TextView tvLeftName = viewHolder.getView(R.id.tv_book_left_name);
                TextView tvMiddleName = viewHolder.getView(R.id.tv_book_middle_name);
                TextView tvRightName = viewHolder.getView(R.id.tv_book_right_name);

                llLeftLabel.setVisibility(VISIBLE);
                llMiddleLabel.setVisibility(VISIBLE);
                llRightLabel.setVisibility(VISIBLE);
                ImageUtils.load(mContext, leftBook.getPicPath(), ivLeftBook);
                tvLeftName.setText(leftBook.getPicName());

                llLeftLabel.setTag(leftBook);
                llMiddleLabel.setTag(leftBook);
                llRightLabel.setTag(leftBook);
                llLeftLabel.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ReadbookModel leftBook = (ReadbookModel) v.getTag();
                        BookDetailActivity.trendTo(mContext);
                    }
                });

                if(middleBook == null){
                    llMiddleLabel.setVisibility(INVISIBLE);
                    llRightLabel.setVisibility(INVISIBLE);
                }else if(rightBook == null){
                    llRightLabel.setVisibility(INVISIBLE);
                    ImageUtils.load(mContext, middleBook.getPicPath(), ivMiddleBook);
                    tvMiddleName.setText(middleBook.getPicName());
                }else{
                    ImageUtils.load(mContext, middleBook.getPicPath(), ivMiddleBook);
                    tvMiddleName.setText(middleBook.getPicName());
                    ImageUtils.load(mContext, rightBook.getPicPath(), ivRightBook);
                    tvRightName.setText(rightBook.getPicName());
                }
            }

            @Override
            public int getCount() {
                int size = mDatas.size();
                if (size == 0) {
                    return size;
                }
                return (mDatas.size() - 1) / 3 + 1;
            }
        };
    }

    @Override
    public void requestData() {
        new NetUtils.Builder()
                .url(URL_BOOK_LIST)
                .param("token", SPUtils.getInstance(mContext).getString(SPUtils.USER_TOKEN))
                .context(mContext)
                .build()
                .model(new ResponseCallback<List<ReadbookModel>>() {
                    @Override
                    public void success(List<ReadbookModel> models) {
                        dealSuccessData(models);
                    }

                    @Override
                    public void failure(String code, String msg) {
                        dealFailureData();
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
