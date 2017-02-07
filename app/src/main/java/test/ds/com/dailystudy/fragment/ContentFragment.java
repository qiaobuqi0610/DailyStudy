package test.ds.com.dailystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.adapter.HotAdapter;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.BeanHotContent;
import test.ds.com.dailystudy.bean.BeanHotTitle;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/2/7 10:37.
 */

public class ContentFragment extends BaseFragment {

    private RecyclerView content_recycler;
    private String order;
    private ArrayList<BeanHotTitle.DataBean> dataList;
    List<BeanHotContent.DataBean> titleList = new ArrayList<>();

    @Override
    public View setSuccView() {
        View view = View.inflate(getActivity(), R.layout.content_recycler, null);
        content_recycler = (RecyclerView) view.findViewById(R.id.content_recycler);
        order = getArguments().getString("order");
        initData(order);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initData(String oeder) {
        //nid=12&order=1&page=1
        LogUtils.i("TAG", "得到的id" + oeder);
        HashMap<String, String> map = new HashMap<>();
        map.put("nid", "12");
        map.put("order", oeder);
        map.put("page", "1");
        new BaseData() {
            @Override
            public void setResultData(String data) {
                LogUtils.i("TAG", "得到的数据" + data);
                ContentFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                BeanHotContent beanHotContent = gson.fromJson(data, BeanHotContent.class);
                titleList = beanHotContent.getData();
                LogUtils.i("TAG", "数据长度" + titleList.size());
                content_recycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                HotAdapter hotAdapter = new HotAdapter(getActivity(), R.layout.hot_recycler_item, titleList, null);
                content_recycler.setAdapter(hotAdapter);
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                ContentFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);
            }
        }.postData(false, false, URLUtils.sort_url, URLUtils.contenNew, map, BaseData.NOTIME);
    }

    @Override
    public View setTitleView() {
        return null;
    }

    public Fragment getOrder(String order) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("order", order);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }
}
