package test.ds.com.dailystudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.activity.GeRenActivity;
import test.ds.com.dailystudy.activity.MineLoginActivity;
import test.ds.com.dailystudy.adapter.MineListViewAdapter;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.MineBean;
import test.ds.com.dailystudy.utils.LogUtils;
import test.ds.com.dailystudy.utils.SharedPreferencesUtils;
import test.ds.com.dailystudy.view.ShowingPage;


/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private View view;
    private ListView listView;
    private ArrayList<MineBean> arrayList = new ArrayList<>();
    private MineListViewAdapter mineListViewAdapter;
    private AutoLinearLayout old;
    private AutoLinearLayout later;
    private ImageView touxiang;
    private TextView title_name;
    private String name;
    private String icon;
    private AutoRelativeLayout relativeLayout;

    @Override
    public View setSuccView() {
        //查找控件
        initview();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MineFragment.this.showingPage.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
        //数据源
        initdata();
        //设置适配器
        if (mineListViewAdapter == null) {
            mineListViewAdapter = new MineListViewAdapter(getActivity(), arrayList);
        } else {
            mineListViewAdapter.notifyDataSetChanged();
        }
        listView.setAdapter(mineListViewAdapter);
        //取数据
    }

    @Override
    public View setTitleView() {
        return null;
    }

    private void initdata() {
        arrayList.clear();
        arrayList.add(new MineBean(R.mipmap.mya, "我的课程"));
        arrayList.add(new MineBean(R.mipmap.myb, "收藏"));
        arrayList.add(new MineBean(R.mipmap.coupon, "优惠券"));
        arrayList.add(new MineBean(R.mipmap.myd, "我的消息"));
        arrayList.add(new MineBean(R.mipmap.mye, "意见反馈"));
        arrayList.add(new MineBean(R.mipmap.myf, "设置"));

    }

    private void initview() {
        view = View.inflate(getActivity(), R.layout.fragment_mine, null);
        listView = (ListView) view.findViewById(R.id.lv);
        old = (AutoLinearLayout) view.findViewById(R.id.old);
        old.setOnClickListener(this);
        later = (AutoLinearLayout) view.findViewById(R.id.later);
        later.setOnClickListener(this);
        touxiang = (ImageView) view.findViewById(R.id.touxiang1);
        touxiang.setOnClickListener(this);
        title_name = (TextView) view.findViewById(R.id.title_name);
        relativeLayout = (AutoRelativeLayout) view.findViewById(R.id.limian);
        relativeLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.old:
                startActivity(new Intent(getActivity(), MineLoginActivity.class));
                break;
            case R.id.limian:
                Intent intent = new Intent(getActivity(), GeRenActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("icon", icon);
                startActivity(intent);
                break;


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        name = SharedPreferencesUtils.getString(getActivity(), "name", null);
        icon = SharedPreferencesUtils.getString(getActivity(), "icon", null);
        LogUtils.i("TAG", "------" + name + "----------------" + icon + "----------------------------");
        if (name != null && icon != null) {
            later.setVisibility(View.VISIBLE);
            old.setVisibility(View.GONE);
            title_name.setText(name);
            Glide.with(MineFragment.this)
                    .load(icon)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(touxiang);


        } else {
            later.setVisibility(View.GONE);
            old.setVisibility(View.VISIBLE);

        }

    }
}
