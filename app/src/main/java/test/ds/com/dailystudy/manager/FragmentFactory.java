package test.ds.com.dailystudy.manager;

import android.support.v4.app.Fragment;

import java.util.HashMap;

import test.ds.com.dailystudy.fragment.CicleFragment;
import test.ds.com.dailystudy.fragment.CircleAttenFragment;
import test.ds.com.dailystudy.fragment.CircleHotFragment;
import test.ds.com.dailystudy.fragment.CircleTopicFragment;
import test.ds.com.dailystudy.fragment.HomeFragment;
import test.ds.com.dailystudy.fragment.MineFragment;
import test.ds.com.dailystudy.fragment.TypeFragment;


/**
 * Created by lenovo on 2017/1/12.
 */

public class FragmentFactory {

    public static HashMap<String, Fragment> fragmentMap = new HashMap<>();
    private static Fragment fragment;

    public static Fragment getFragment(String sign) {

        fragment = fragmentMap.get(sign);

        if (fragment != null) {
            return fragment;
        } else if (sign.equals("HomePagerFragment")) {
            fragment = new HomeFragment();
        } else if (sign.equals("CategoryFragment")) {
            fragment = new TypeFragment();
        } else if (sign.equals("CircleFragment")) {
            fragment = new CicleFragment();
        } else if (sign.equals("MineFragment")) {
            fragment = new MineFragment();
        } else if (sign.equals("话题")) {
            fragment = new CircleTopicFragment();
        } else if (sign.equals("热门")) {
            fragment = new CircleHotFragment();
        } else if (sign.equals("关注")) {
            fragment = new CircleAttenFragment();
        }

        fragmentMap.put(sign, fragment);

        return fragment;
    }

}
