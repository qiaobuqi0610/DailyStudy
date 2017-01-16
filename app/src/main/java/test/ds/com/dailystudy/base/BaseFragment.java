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
 * on 2017/1/13 21:21.
 */

public abstract class BaseFragment extends Fragment {

    public ShowingPage showingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        showingPage = new ShowingPage(getActivity()) {

            @Override
            public View setSuccView() {
                return BaseFragment.this.setSuccView();
            }

            @Override
            public View setTitleView() {
                return BaseFragment.this.setTitleView();
            }
        };
        return showingPage;
    }

    public abstract View setSuccView();

    public abstract View setTitleView();
}
