package test.ds.com.dailystudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.ds.com.dailystudy.R;
import test.ds.com.dailystudy.utils.CommonUtils;


/**
 * Created by PC on 2017/1/12.
 */

public class CircleAttenFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= CommonUtils.inflate(R.layout.circle_atten_fragment);
        return view;
    }
}
