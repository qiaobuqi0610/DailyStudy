package test.ds.com.dailystudy.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.adapter.MineListViewAdapter;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.MineBean;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class MineFragment extends BaseFragment {

    private View view;
    private ListView listView;
    private ArrayList<MineBean> arrayList = new ArrayList<>();
    private MineListViewAdapter mineListViewAdapter;

    @Override
    protected void onLoad() {
        this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
        //查找控件
        initview();
        //数据源
        initdata();
        //设置适配器
        if (mineListViewAdapter == null) {
            mineListViewAdapter = new MineListViewAdapter(getActivity(), arrayList);
        }else{
            mineListViewAdapter.notifyDataSetChanged();
        }
        listView.setAdapter(mineListViewAdapter);
        return view;
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
    }

}
