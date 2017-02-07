package test.ds.com.dailystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.ds.com.dailystudy.R;

/**
 * Created by 乔智锋
 * on 2017/1/10 21:48.
 */

public class PlFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view=inflater.inflate(R.layout.layout_pinglun,null);
        return view;
    }
}
