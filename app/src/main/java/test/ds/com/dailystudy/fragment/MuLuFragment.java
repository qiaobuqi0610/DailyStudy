package test.ds.com.dailystudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.Bean_MuLu;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class MuLuFragment extends BaseFragment {

    private String pid;
    private Bean_MuLu courseData;
    private ListView listView;
    private ArrayList<Bean_MuLu.DataBean.NodesBean> list;

    @Override
    public View setSuccView() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_mulu_listview,null);
        listView = (ListView) inflate.findViewById(R.id.listView);
        pid = getArguments().getString("id");
        //找控件
        initData();
        return inflate;
    }
    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("courseid", pid);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                MuLuFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                list = new ArrayList<Bean_MuLu.DataBean.NodesBean>();
                courseData = gson.fromJson(data, Bean_MuLu.class);
                for (int i = 0; i <courseData.getData().size() ; i++) {
                    list.addAll(courseData.getData().get(i).getNodes());
                }
                listView.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return list.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        view= CommonUtils.inflate(R.layout.course_mulu_item_listview_item);
                        TextView textView = (TextView) view.findViewById(R.id.iv_index);
                        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                        tv_name.setText(list.get(i).getSections_name());
                        return view;
                    }
                });
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {

            }
        }.postData(false, false, URLUtils.sort_mulu_url, URLUtils.sort_mulu_url2, map, BaseData.NOTIME);
    }

    @Override
    public View setTitleView() {
        return null;
    }
    public Fragment chuanzhi(String id){
        MuLuFragment xqfragment=new MuLuFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        xqfragment.setArguments(bundle);
        return   xqfragment;
    }
}
