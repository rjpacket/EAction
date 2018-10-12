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
import com.rjp.eaction.bean.ProvinceBean;
import com.rjp.eaction.swiper.ImageSwiper;
import com.rjp.eaction.swiper.SwiperView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProvinceFragment extends BaseFragment {

    @BindView(R.id.province_list_view)
    ListView listView;
    private ArrayList<ProvinceBean> provinces = new ArrayList<>();
    private int selectedIndex = -1;
    private OnProvinceSelectListener onProvinceSelectListener;
    private CommonAdapter<ProvinceBean> adapter;

    public ProvinceFragment() {
        // Required empty public constructor
    }

    @Override
    protected void networkReload() {

    }

    @Override
    protected void handle() {
        listView.setAdapter(adapter = new CommonAdapter<ProvinceBean>(mContext, R.layout.item_province_list_view, provinces) {
            @Override
            protected void convert(ViewHolder viewHolder, ProvinceBean item, int position) {
                TextView tvName = viewHolder.getView(R.id.tv_name);
                tvName.setText(item.getName());
                tvName.setTextColor(selectedIndex == position ? getResources().getColor(R.color.main_color) : getResources().getColor(R.color.text_333333));
                tvName.setTag(position);
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectedIndex = (Integer) v.getTag();
                        notifyDataSetChanged();
                        if(onProvinceSelectListener != null){
                            onProvinceSelectListener.onProvinceSelect(provinces.get(selectedIndex));
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
        return "省";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_province;
    }

    public void setOnProvinceSelectListener(OnProvinceSelectListener onProvinceSelectListener) {
        this.onProvinceSelectListener = onProvinceSelectListener;
    }

    public void setData(List<ProvinceBean> provinceBeans) {
        selectedIndex = -1;
        provinces.addAll(provinceBeans);
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
