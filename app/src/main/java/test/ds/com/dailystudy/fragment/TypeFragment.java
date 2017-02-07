package test.ds.com.dailystudy.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.adapter.MyGridView_Adapter;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.Bean_Sort;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class TypeFragment extends BaseFragment implements View.OnClickListener {

    private TextView sort_icon_iv1;
    private TextView sort_icon_iv2;
    private TextView sort_icon_iv3;
    private TextView sort_icon_iv4;
    private TextView sort_icon_iv5;
    private TextView sort_icon_iv6;
    private AutoRelativeLayout sort_rl_item1;
    private AutoRelativeLayout sort_rl_item2;
    private AutoRelativeLayout sort_rl_item3;
    private AutoRelativeLayout sort_rl_item4;
    private AutoRelativeLayout sort_rl_item5;
    private AutoRelativeLayout sort_rl_item6;
    private GridView gridView1;
    private GridView gridView2;
    private GridView gridView3;
    private GridView gridView4;
    private GridView gridView5;
    private GridView gridView6;
    private Bean_Sort[] bean_sort;
    ArrayList<ArrayList<Bean_Sort.NodesBean>> listAll = new ArrayList<ArrayList<Bean_Sort.NodesBean>>();
    private ArrayList<Bean_Sort.NodesBean> list;
    private ImageView sort_iv1;
    private ImageView sort_iv2;
    private ImageView sort_iv3;
    private ImageView sort_iv4;
    private ImageView sort_iv5;
    private ImageView sort_iv6;
    private boolean flag = false;
    private ArrayList<MyGridView_Adapter> list_adapter = new ArrayList<>();//将adapter添加到集合中
    private ArrayList<GridView> listGv;
    private int lastPosition = -1;
    private ArrayList<ImageView> list_image;
    private ArrayList<TextView> list_color;
    private ArrayList<Integer> list_image2;
    private ArrayList<String> list_color2;
    private AutoRelativeLayout sort_top_search;


    private void setTvIv() {
        list_image2 = new ArrayList<>();
        list_image2.add(R.mipmap.heart_s);
        list_image2.add(R.mipmap.coffee_s);
        list_image2.add(R.mipmap.diamond_s);
        list_image2.add(R.mipmap.heart_s);
        list_image2.add(R.mipmap.hat_s);
        list_image2.add(R.mipmap.language_s);
        list_image = new ArrayList<>();
        list_image.add(sort_iv1);
        list_image.add(sort_iv2);
        list_image.add(sort_iv3);
        list_image.add(sort_iv4);
        list_image.add(sort_iv5);
        list_image.add(sort_iv6);
        list_color = new ArrayList<>();
        list_color.add(sort_icon_iv1);
        list_color.add(sort_icon_iv2);
        list_color.add(sort_icon_iv3);
        list_color.add(sort_icon_iv4);
        list_color.add(sort_icon_iv5);
        list_color.add(sort_icon_iv6);
        list_color2 = new ArrayList<>();
        list_color2.add("#f95d51");
        list_color2.add("#ff9c00");
        list_color2.add("#7dcd43");
        list_color2.add("#f95d51");
        list_color2.add("#34b3ee");
        list_color2.add("#90369a");
    }

    public void setCurrentAdapter(int position) {
        for (int i = 0; i < listGv.size(); i++) {
            list_image.get(i).setImageResource(R.mipmap.down);
            listGv.get(i).setVisibility(View.GONE);
            list_color.get(i).setTextColor(Color.BLACK);
        }
        if (flag && lastPosition == position) {
            list_color.get(position).setTextColor(Color.BLACK);
            list_image.get(position).setImageResource(R.mipmap.down);
            listGv.get(position).setVisibility(View.GONE);
        } else {
            list_image.get(position).setImageResource(list_image2.get(position));
            list_color.get(position).setTextColor(Color.parseColor(list_color2.get(position)));
            listGv.get(position).setVisibility(View.VISIBLE);
            lastPosition = position;
        }
        flag = !flag;
        for (int i = 0; i < listGv.size(); i++) {
            list_adapter.get(i).notifyDataSetChanged();
        }
    }

    private void initAddAdapter() {
        listGv = new ArrayList<>();
        listGv.add(gridView1);
        listGv.add(gridView2);
        listGv.add(gridView3);
        listGv.add(gridView4);
        listGv.add(gridView5);
        listGv.add(gridView6);
        MyGridView_Adapter adapter1 = new MyGridView_Adapter(getActivity(), listAll.get(0), bean_sort);

        gridView1.setAdapter(adapter1);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        MyGridView_Adapter adapter2 = new MyGridView_Adapter(getActivity(), listAll.get(1), bean_sort);
        gridView2.setAdapter(adapter2);
        MyGridView_Adapter adapter3 = new MyGridView_Adapter(getActivity(), listAll.get(2), bean_sort);
        gridView3.setAdapter(adapter3);
        MyGridView_Adapter adapter4 = new MyGridView_Adapter(getActivity(), listAll.get(3), bean_sort);
        gridView4.setAdapter(adapter4);
        MyGridView_Adapter adapter5 = new MyGridView_Adapter(getActivity(), listAll.get(4), bean_sort);
        gridView5.setAdapter(adapter5);
        MyGridView_Adapter adapter6 = new MyGridView_Adapter(getActivity(), listAll.get(5), bean_sort);
        gridView6.setAdapter(adapter6);

        list_adapter.add(adapter1);
        list_adapter.add(adapter2);
        list_adapter.add(adapter3);
        list_adapter.add(adapter4);
        list_adapter.add(adapter5);
        list_adapter.add(adapter6);
    }

    private void initClint() {
        sort_rl_item1.setOnClickListener(this);
        sort_rl_item2.setOnClickListener(this);
        sort_rl_item3.setOnClickListener(this);
        sort_rl_item4.setOnClickListener(this);
        sort_rl_item5.setOnClickListener(this);
        sort_rl_item6.setOnClickListener(this);

    }

    private void initData() {
        new BaseData() {
            @Override
            public void setResultData(String data) {
                TypeFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                bean_sort = gson.fromJson(data, Bean_Sort[].class);
                sort_icon_iv1.setText(bean_sort[0].getCname());
                sort_icon_iv2.setText(bean_sort[1].getCname());
                sort_icon_iv3.setText(bean_sort[2].getCname());
                sort_icon_iv4.setText(bean_sort[3].getCname());
                sort_icon_iv5.setText(bean_sort[4].getCname());
                sort_icon_iv6.setText(bean_sort[5].getCname());

                for (int i = 0; i < bean_sort.length; i++) {
                    list = new ArrayList<>();
                    for (int j = 0; j < bean_sort[i].getNodes().size(); j++) {
                        list.add(bean_sort[i].getNodes().get(j));
                    }
                    listAll.add(list);
                }
                initAddAdapter();
                setCurrentAdapter(0);
                list_color.get(0).setTextColor(Color.parseColor(list_color2.get(0)));
                list_image.get(0).setImageResource(list_image2.get(0));
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                TypeFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        }.getData(URLUtils.sort_url, URLUtils.sort_canshu, BaseData.NORMALTIME);

    }

    private void intView(View view) {
        sort_icon_iv1 = (TextView) view.findViewById(R.id.sort_tv1);
        sort_icon_iv2 = (TextView) view.findViewById(R.id.sort_tv2);
        sort_icon_iv3 = (TextView) view.findViewById(R.id.sort_tv3);
        sort_icon_iv4 = (TextView) view.findViewById(R.id.sort_tv4);
        sort_icon_iv5 = (TextView) view.findViewById(R.id.sort_tv5);
        sort_icon_iv6 = (TextView) view.findViewById(R.id.sort_tv6);
        sort_rl_item1 = (AutoRelativeLayout) view.findViewById(R.id.sort_rl_item1);
        sort_rl_item2 = (AutoRelativeLayout) view.findViewById(R.id.sort_rl_item2);
        sort_rl_item3 = (AutoRelativeLayout) view.findViewById(R.id.sort_rl_item3);
        sort_rl_item4 = (AutoRelativeLayout) view.findViewById(R.id.sort_rl_item4);
        sort_rl_item5 = (AutoRelativeLayout) view.findViewById(R.id.sort_rl_item5);
        sort_rl_item6 = (AutoRelativeLayout) view.findViewById(R.id.sort_rl_item6);
        gridView1 = (GridView) view.findViewById(R.id.sort_gridview1);
        gridView2 = (GridView) view.findViewById(R.id.sort_gridview2);
        gridView3 = (GridView) view.findViewById(R.id.sort_gridview3);
        gridView4 = (GridView) view.findViewById(R.id.sort_gridview4);
        gridView5 = (GridView) view.findViewById(R.id.sort_gridview5);
        gridView6 = (GridView) view.findViewById(R.id.sort_gridview6);
        sort_iv1 = (ImageView) view.findViewById(R.id.sort_iv1);
        sort_iv2 = (ImageView) view.findViewById(R.id.sort_iv2);
        sort_iv3 = (ImageView) view.findViewById(R.id.sort_iv3);
        sort_iv4 = (ImageView) view.findViewById(R.id.sort_iv4);
        sort_iv5 = (ImageView) view.findViewById(R.id.sort_iv5);
        sort_iv6 = (ImageView) view.findViewById(R.id.sort_iv6);
        sort_top_search = (AutoRelativeLayout) view.findViewById(R.id.sort_top_search);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sort_rl_item1:
                setCurrentAdapter(0);
                break;
            case R.id.sort_rl_item2:
                setCurrentAdapter(1);

                break;
            case R.id.sort_rl_item3:
                setCurrentAdapter(2);
                break;
            case R.id.sort_rl_item4:
                setCurrentAdapter(3);
                break;
            case R.id.sort_rl_item5:
                setCurrentAdapter(4);
                break;
            case R.id.sort_rl_item6:
                setCurrentAdapter(5);
                break;

        }
    }

    @Override
    public View setSuccView() {
        View view = CommonUtils.inflate(R.layout.fragment_course);
        //找控件
        intView(view);
        setTvIv();

        //听事件
        initClint();

        sort_iv2.setImageResource(R.mipmap.down);
        sort_iv3.setImageResource(R.mipmap.down);
        sort_iv4.setImageResource(R.mipmap.down);
        sort_iv5.setImageResource(R.mipmap.down);
        sort_iv6.setImageResource(R.mipmap.down);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //请求网络
        initData();
    }

    @Override
    public View setTitleView() {
        return null;
    }
}
