package test.ds.com.dailystudy.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.ds.com.dailystudy.view.ShowingPage;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:47.
 */

public abstract class BaseFragment extends Fragment {
    private ShowingPage showingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        showingPage = new ShowingPage(getActivity()) {
            @Override
            protected View createSuccessView() {
                return BaseFragment.this.createSuccessView();
            }

            @Override
            protected void onLoad() {

            }
        };

        BaseFragment.this.onLoad();
        return showingPage;
    }

    //继续抽象给继承自自己的Fragment
    protected abstract void onLoad();

    protected abstract View createSuccessView();

    public void showCurrentPage(ShowingPage.StateType stateType) {
        //调用showingPage的方法
        if (showingPage != null) {
            showingPage.showCurrentPage(stateType);
        }
    }
}
