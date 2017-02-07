package test.ds.com.dailystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.HomeBean;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 吕卓钊
 * on 2017/1/17 14:33.
 */

public class ItemFragemnt extends BaseFragment {


    private HomeBean homeBean;
    private List<HomeBean.DataBean.AdlistBean> adlist;
    private List<HomeBean.DataBean.HotcourseBean> hotcourse;
    private List<HomeBean.DataBean.IndexrecommendBean.TopBean> top;
    private List<HomeBean.DataBean.IndexrecommendBean.ListviewBean> listview;
    private List<HomeBean.DataBean.IndexothersBean> indexothers;
    private ItemFragemnt itemFragemnt;
    private Gson gson;
     static ArrayList<HomeBean.DataBean>  list=new ArrayList();
    @Override
    public View setSuccView() {
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                ItemFragemnt.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                gson = new Gson();
                homeBean = gson.fromJson(data, HomeBean.class);
                list.add(homeBean.getData());
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {
                Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
            }
        }.getData(URLUtils.homeUrl, URLUtils.homeArgs, BaseData.NOTIME);
    }

    public static ItemFragemnt getInstance(String cid) {

        ItemFragemnt itemFragemnt = new ItemFragemnt();
        Bundle args = new Bundle();
        args.putSerializable("cid",list);
        itemFragemnt.setArguments(args);
        return itemFragemnt;
    }


    @Override
    public View setTitleView() {
        return null;
    }
}
