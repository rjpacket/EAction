package com.rjp.eaction.ui.fragments;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.rjp.commonadapter.CommonAdapter;
import com.rjp.commonadapter.ViewHolder;
import com.rjp.eaction.R;
import com.rjp.eaction.baseAF.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaFragment extends BaseFragment {

    @BindView(R.id.area_list_view)
    ListView listView;
    private ArrayList<String> areas = new ArrayList<>();
    private int selectedIndex = -1;
    private OnAreaSelectListener onAreaSelectListener;
    private CommonAdapter<String> adapter;

    public AreaFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        listView.setAdapter(adapter = new CommonAdapter<String>(mContext, R.layout.item_province_list_view, areas) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                TextView tvName = viewHolder.getView(R.id.tv_name);
                tvName.setText(item);
                tvName.setTextColor(selectedIndex == position ? getResources().getColor(R.color.main_color) : getResources().getColor(R.color.text_333333));
                tvName.setTag(position);
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIndex = (Integer) v.getTag();
                        notifyDataSetChanged();
                        if(onAreaSelectListener != null){
                            onAreaSelectListener.onAreaSelect(areas.get(selectedIndex));
                        }
                    }
                });
            }
        });
    }

    @Override
    protected boolean showTopBar() {
        return false;
    }

    @Override
    protected String getPageTitle() {
        return "市";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_area;
    }

    public void setOnAreaSelectListener(OnAreaSelectListener onAreaSelectListener) {
        this.onAreaSelectListener = onAreaSelectListener;
    }

    public void setData(List<String> area) {
        selectedIndex = -1;
        areas.clear();
        areas.addAll(area);
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
