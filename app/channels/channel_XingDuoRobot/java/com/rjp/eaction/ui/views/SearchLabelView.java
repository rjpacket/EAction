package com.rjp.eaction.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.rjp.eaction.R;
import com.rjp.eaction.util.AppUtils;

public class SearchLabelView extends LinearLayout{
    private Context mContext;
    private boolean headIconShowable;

    public SearchLabelView(Context context) {
        this(context, null);
    }

    public SearchLabelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.view_search_view, this);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(0, AppUtils.dp2px(10), 0, AppUtils.dp2px(10));
        ImageView ivHeadIcon = findViewById(R.id.iv_head_icon);
        ImageView ivInputIcon = findViewById(R.id.iv_input_icon);
        LinearLayout llSearchInput = findViewById(R.id.ll_book_search_label);
        TextView tvSearchHint = findViewById(R.id.tv_search_hint);
        if(attrs != null){
            TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.SearchLabelView);
            boolean headIconShowable = array.getBoolean(R.styleable.SearchLabelView_slv_head_icon_showable, true);
            int inputBackground = array.getResourceId(R.styleable.SearchLabelView_slv_input_background, 0);
            int inputIcon = array.getResourceId(R.styleable.SearchLabelView_slv_input_icon, 0);
            int inputTextColor = array.getColor(R.styleable.SearchLabelView_slv_input_text_color, Color.BLACK);
            float inputTextSize = array.getDimension(R.styleable.SearchLabelView_slv_input_text_size, AppUtils.dp2px(12));
            ivHeadIcon.setVisibility(headIconShowable ? VISIBLE : GONE);
            if(inputBackground != 0){
                llSearchInput.setBackgroundResource(inputBackground);
            }
            if(inputIcon != 0){
                ivInputIcon.setImageResource(inputIcon);
            }
            tvSearchHint.setTextColor(inputTextColor);
            tvSearchHint.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize);
        }
    }
}
