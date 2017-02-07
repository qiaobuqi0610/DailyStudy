package test.ds.com.dailystudy.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;

import java.util.ArrayList;
import java.util.HashMap;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.adapter.HotAdapter;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.BeanHotContent;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.MyHeader;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by PC on 2017/1/16.
 */

public class CircleHotVpFragment extends BaseFragment {
    View view;
    HashMap<String, String> argsMap = new HashMap<>();
    ArrayList<BeanHotContent.DataBean> contentList = new ArrayList<>();
    private RecyclerView myRecycler;
    private String title;
    private SpringView mySpringview;
    private ObservableScrollView myScrollView;


    //设置列表
    private void initRecycler() {
        //设置布局管理器
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotAdapter hotAdapter = new HotAdapter(getActivity(), R.layout.hot_recycler_item, contentList, title);
        //设置适配器
        myRecycler.setAdapter(hotAdapter);

    }

    public static Fragment getFragment(String title, String tid) {
        Fragment f1 = new CircleHotVpFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tid", tid);
        bundle.putString("title", title);
        f1.setArguments(bundle);
        return f1;
    }

    @Override
    public View setSuccView() {
        view = View.inflate(getActivity(), R.layout.circle_hot_vp_fragment, null);
        myRecycler = (RecyclerView) view.findViewById(R.id.circle_hot_vp_recyclerView);
        //把参数放入集合
        String tid = getArguments().getString("tid");
        title = getArguments().getString("title");
        LogUtils.i("TAG", "Title数据" + title);
        argsMap.put("tid", tid);
        argsMap.put("page", "1");
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToRecyclerView(myRecycler);
        mySpringview = (SpringView) view.findViewById(R.id.content);
        mySpringview.setHeader(new MyHeader());
        mySpringview.setType(SpringView.Type.FOLLOW);
        mySpringview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mySpringview.onFinishFreshAndLoad();
                    }
                }, 1500);
            }

            @Override
            public void onLoadmore() {
            }
        });
        new BaseData() {

            @Override
            public void setResultData(String data) {
                CircleHotVpFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);

                Gson gson = new Gson();
                BeanHotContent beanHotContent = gson.fromJson(data, BeanHotContent.class);

                contentList = (ArrayList<BeanHotContent.DataBean>) beanHotContent.getData();
                initRecycler();

            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                CircleHotVpFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_ERROR);

            }
        }.postData(false, false, URLUtils.sort_url, URLUtils.circle_hot_content, argsMap, BaseData.NOTIME);
        return view;
    }

    @Override
    public View setTitleView() {
        return null;
    }
}
