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
import com.rjp.eaction.bean.CityBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityFragment extends BaseFragment {

    @BindView(R.id.city_list_view)
    ListView listView;
    private ArrayList<CityBean> citys = new ArrayList<>();
    private int selectedIndex = -1;
    private OnCitySelectListener onCitySelectListener;
    private CommonAdapter<CityBean> adapter;

    public CityFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        listView.setAdapter(adapter = new CommonAdapter<CityBean>(mContext, R.layout.item_province_list_view, citys) {
            @Override
            protected void convert(ViewHolder viewHolder, CityBean item, int position) {
                TextView tvName = viewHolder.getView(R.id.tv_name);
                tvName.setText(item.getName());
                tvName.setTextColor(selectedIndex == position ? getResources().getColor(R.color.main_color) : getResources().getColor(R.color.text_333333));
                tvName.setTag(position);
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIndex = (Integer) v.getTag();
                        notifyDataSetChanged();
                        if(onCitySelectListener != null){
                            onCitySelectListener.onCitySelect(citys.get(selectedIndex));
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
        return R.layout.fragment_city;
    }

    public void setOnCitySelectListener(OnCitySelectListener onCitySelectListener) {
        this.onCitySelectListener = onCitySelectListener;
    }

    public void setData(List<CityBean> city) {
        selectedIndex = -1;
        citys.clear();
        citys.addAll(city);
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
