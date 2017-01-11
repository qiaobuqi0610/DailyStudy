package test.ds.com.dailystudy.fragment;

import android.view.View;
import android.widget.TextView;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.view.ShowingPage;

import static test.ds.com.dailystudy.R.id.sort_tv1;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class TypeFragment extends BaseFragment {

    private TextView text_sort_tv1;
    private TextView text_sort_tv2;
    private TextView text_sort_tv3;
    private TextView text_sort_tv4;
    private TextView text_sort_tv5;
    private TextView text_sort_tv6;
    @Override
    protected void onLoad() {
        TypeFragment.this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_course);
        //找控件
        intView(view);
        //请求网络
        return view;
    }

 /*   private void initData() {
        new BaseData() {
            @Override
            public void setResultData(String data) {

            }

            @Override
            protected void setResulttError(ShowingPage.StateType stateLoadError) {

            }
        }.getData();
    }*/

    private void intView(View view) {
        text_sort_tv1 = (TextView) view.findViewById(sort_tv1);
        text_sort_tv2 = (TextView) view.findViewById(R.id.sort_tv2);
        text_sort_tv3 = (TextView) view.findViewById(R.id.sort_tv3);
        text_sort_tv4 = (TextView) view.findViewById(R.id.sort_tv4);
        text_sort_tv5 = (TextView) view.findViewById(R.id.sort_tv5);
        text_sort_tv6 = (TextView) view.findViewById(R.id.sort_tv6);
    }
}
