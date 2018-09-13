package com.rjp.eaction.ui.views;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.widget.Toast;

import com.rjp.eaction.utils.EmojiFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AvoidEmojiEditText extends android.support.v7.widget.AppCompatEditText {
    private Context mContext;

    private InputFilter emojiFilter;

    public AvoidEmojiEditText(Context context) {
        this(context, null);
    }

    public AvoidEmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initEditText();
    }

    // 初始化edittext 控件
    private void initEditText() {
        emojiFilter = new InputFilter() {

            Pattern emoji = Pattern.compile("[^\\u0020-\\u007E\\u00A0-\\u00BE\\u2E80-\\uA4CF\\uF900-\\uFAFF\\uFE30-\\uFE4F\\uFF00-\\uFFEF\\u0080-\\u009F\\u2000-\\u201f\r\n]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    Toast.makeText(mContext, "不支持表情输入", Toast.LENGTH_SHORT).show();
                    return "";
                }
                return null;
            }
        };
        setFilters(new InputFilter[]{new EmojiFilter(mContext), emojiFilter});
    }

}