package com.rjp.eaction.views.decorations;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.rjp.eaction.R;
import com.rjp.eaction.util.AppUtils;

public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;


    public SimplePaddingDecoration(Context context, int height) {
        dividerHeight = height;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = dividerHeight;
    }
}