package com.rjp.eaction.util.popup;

import com.rjp.commonadapter.ViewHolder;

/**
 * author : Gimpo create on 2018/7/20 11:04
 * email  : jimbo922@163.com
 */
public interface OnPopupBindDataListener<T> {
    void convert(ViewHolder viewHolder, T item, int position);
}
