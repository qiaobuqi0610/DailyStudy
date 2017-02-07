package test.ds.com.dailystudy.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseData;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.Bean_Xq;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.URLUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class XqFragment extends BaseFragment {

    private TextView teacher_name;
    private TextView school_name;
    private String pid;
    private Bean_Xq courseData;
    private TextView school_des;

    @Override
    public View setSuccView() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.layout_xq_listview,null);
        pid = getArguments().getString("id");
        //找控件
        teacher_name = (TextView) inflate.findViewById(R.id.tv_coursee_detail_teacher);
        school_name = (TextView) inflate.findViewById(R.id.tv_coursee_detail_school);
        school_des = (TextView) inflate.findViewById(R.id.tv_coursee_detail_des);
        initData();
        return inflate;
    }


    private void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("courseid", pid);
        new BaseData() {
            @Override
            public void setResultData(String data) {
                XqFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
                Gson gson = new Gson();
                courseData = gson.fromJson(data, Bean_Xq.class);
                LogUtils.i("TAG","111111111112222222"+courseData.getData().getDes());
                teacher_name.setText("讲师:"+courseData.getData().getCourse_tname());
                school_name.setText("学校:"+courseData.getData().getSchool_name());
                school_des.setText(courseData.getData().getDes());
            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {

            }
        }.postData(false, false, URLUtils.sort_sq_url, URLUtils.sort_sq_url2, map, BaseData.NOTIME);
    }

    @Override
    public View setTitleView() {
        return null;
    }
    public Fragment chuanzhi(String id){
        XqFragment xqfragment=new XqFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        xqfragment.setArguments(bundle);
        return   xqfragment;
    }
}
