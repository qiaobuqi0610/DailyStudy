package test.ds.com.dailystudy.fragment;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.adapter.MineListViewAdapter;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.bean.MineBean;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class MineFragment extends BaseFragment {

    private View view;
    private ListView listView;
   ArrayList<MineBean> arrayList=new ArrayList<>();
    @Override
    protected void onLoad() {
        this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
        view = CommonUtils.inflate(R.layout.fragment_mine);
        listView = (ListView) view.findViewById(R.id.lv);
        MineBean mineBean1=new MineBean(R.mipmap.mya,"我的课程");
        MineBean mineBean2=new MineBean(R.mipmap.myb,"收藏");
        MineBean mineBean3=new MineBean(R.mipmap.coupon,"优惠券");
        MineBean mineBean4=new MineBean(R.mipmap.myd,"我的消息");
        MineBean mineBean5=new MineBean(R.mipmap.mye,"意见反馈");
        MineBean mineBean6=new MineBean(R.mipmap.myf,"设置");
        arrayList.add(mineBean1);
        arrayList.add(mineBean2);
        arrayList.add(mineBean3);
        arrayList.add(mineBean4);
        arrayList.add(mineBean5);
        arrayList.add(mineBean6);
        listView.setAdapter(new MineListViewAdapter(getActivity(),arrayList));
        return view;
    }
}
