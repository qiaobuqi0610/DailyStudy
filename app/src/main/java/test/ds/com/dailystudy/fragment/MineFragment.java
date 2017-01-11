package test.ds.com.dailystudy.fragment;

import android.view.View;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.base.BaseFragment;
import test.ds.com.dailystudy.utils.CommonUtils;
import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class MineFragment extends BaseFragment {
    @Override
    protected void onLoad() {
        this.showCurrentPage(ShowingPage.StateType.STATE_LOAD_SUCCESS);
    }

    @Override
    protected View createSuccessView() {
        View view = CommonUtils.inflate(R.layout.fragment_mine);
        return view;
    }
}
